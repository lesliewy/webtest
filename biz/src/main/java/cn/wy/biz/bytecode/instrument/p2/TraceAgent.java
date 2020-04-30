package cn.wy.biz.bytecode.instrument.p2;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

/**
 * <pre>
 *     agent 使用jvmti 的attach api 技术在运行时修改字节码.
 *     instrument 中这里使用了asm core api 来修改字节码.
 *     1, jar cvfm wyagent.jar manifest.txt /Users/leslie/MyProjects/GitHub/webtest/biz/target/classes/cn/wy/biz/bytecode/instrument/p2/HelloTraceAgent.class
 *     2, 执行 HelloTraceAgent.main()
 *     3, 修改 TraceAgentMain 中的pid, 并执行其main()
 *     4, TraceAgentMain 的server端就能收到信息.
 * </pre>
 * 
 * Created by leslie on 2020/4/30.
 */
public class TraceAgent {

    public static void agentmain(String args, Instrumentation instrumentation) throws ClassNotFoundException,
                                                                               UnmodifiableClassException {
        if (args == null) {
            return;
        }
        int index = args.lastIndexOf(".");
        if (index != -1) {
            String className = args.substring(0, index);
            String methodName = args.substring(index + 1);
            // 目标代码已经加载，需要重新触发加载流程，才会通过注册的转换器进行转换
            instrumentation.addTransformer(new TraceClassFileTransformer(className.replace(".", "/"), methodName),
                                           true);
            instrumentation.retransformClasses(Class.forName(className));
        }
    }

    public static class TraceClassFileTransformer implements ClassFileTransformer {

        private String traceClassName;
        private String traceMethodName;

        public TraceClassFileTransformer(String traceClassName, String traceMethodName){
            this.traceClassName = traceClassName;
            this.traceMethodName = traceMethodName;
        }

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            // 过滤掉Jdk、agent、非指定类的方法
            if (className.startsWith("java") || className.startsWith("jdk") || className.startsWith("javax")
                || className.startsWith("sun") || className.startsWith("com/sun")
                || className.startsWith("org/xunche/agent") || !className.equals(traceClassName)) {
                // return null会执行原来的字节码
                return null;
            }
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            reader.accept(new TraceVisitor(className, traceMethodName, writer), ClassReader.EXPAND_FRAMES);
            return writer.toByteArray();
        }
    }

    public static class TraceVisitor extends ClassVisitor {

        private String className;
        private String traceMethodName;

        public TraceVisitor(String className, String traceMethodName, ClassVisitor classVisitor){
            super(Opcodes.ASM5, classVisitor);
            this.className = className;
            this.traceMethodName = traceMethodName;
        }

        @Override
        public MethodVisitor visitMethod(int methodAccess, String methodName, String methodDesc, String signature,
                                         String[] exceptions) {
            MethodVisitor methodVisitor = cv.visitMethod(methodAccess, methodName, methodDesc, signature, exceptions);
            if (traceMethodName.equals(methodName)) {
                return new TraceAdviceAdapter(className, methodVisitor, methodAccess, methodName, methodDesc);
            }
            return methodVisitor;
        }
    }

    private static class TraceAdviceAdapter extends AdviceAdapter {

        private final String   className;
        private final String   methodName;
        private final Type[]   methodArgs;
        private final String[] parameterNames;
        private final int[]    lvtSlotIndex;

        protected TraceAdviceAdapter(String className, MethodVisitor methodVisitor, int methodAccess, String methodName,
                                     String methodDesc){
            super(Opcodes.ASM5, methodVisitor, methodAccess, methodName, methodDesc);
            this.className = className;
            this.methodName = methodName;
            this.methodArgs = Type.getArgumentTypes(methodDesc);
            this.parameterNames = new String[this.methodArgs.length];
            this.lvtSlotIndex = computeLvtSlotIndices(isStatic(methodAccess), this.methodArgs);
        }

        @Override
        public void visitLocalVariable(String name, String description, String signature, Label start, Label end,
                                       int index) {
            for (int i = 0; i < this.lvtSlotIndex.length; ++i) {
                if (this.lvtSlotIndex[i] == index) {
                    this.parameterNames[i] = name;
                }
            }
        }

        /**
         * <pre>
         *     获取请求参数和返回参数并调用 Sender.send 方法
         * </pre>
         * 
         * @param opcode
         */
        @Override
        protected void onMethodExit(int opcode) {
            // 排除构造方法和静态代码块
            if ("<init>".equals(methodName) || "<clinit>".equals(methodName)) {
                return;
            }
            if (opcode == RETURN) {
                push((Type) null);
            } else if (opcode == LRETURN || opcode == DRETURN) {
                dup2();
                box(Type.getReturnType(methodDesc));
            } else {
                dup();
                box(Type.getReturnType(methodDesc));
            }
            Type objectType = Type.getObjectType("java/lang/Object");
            push(lvtSlotIndex.length);
            newArray(objectType);
            for (int j = 0; j < lvtSlotIndex.length; j++) {
                int index = lvtSlotIndex[j];
                Type type = methodArgs[j];
                dup();
                push(j);
                mv.visitVarInsn(ALOAD, index);
                box(type);
                arrayStore(objectType);
            }
            visitLdcInsn(className.replace("/", "."));
            visitLdcInsn(methodName);
            mv.visitMethodInsn(INVOKESTATIC, "cn/wy/biz/bytecode/instrument/p2/Sender", "send",
                               "(Ljava/lang/Object;[Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V", false);
        }

        private static int[] computeLvtSlotIndices(boolean isStatic, Type[] paramTypes) {
            int[] lvtIndex = new int[paramTypes.length];
            int nextIndex = isStatic ? 0 : 1;
            for (int i = 0; i < paramTypes.length; ++i) {
                lvtIndex[i] = nextIndex;
                if (isWideType(paramTypes[i])) {
                    nextIndex += 2;
                } else {
                    ++nextIndex;
                }
            }
            return lvtIndex;
        }

        private static boolean isWideType(Type aType) {
            return aType == Type.LONG_TYPE || aType == Type.DOUBLE_TYPE;
        }

        private static boolean isStatic(int access) {
            return (access & 8) > 0;
        }
    }
}
