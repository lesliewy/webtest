package cn.wy.biz.bytecode.asm.p3;

import org.objectweb.asm.*;

/**
 * Created by leslie on 2020/4/29.
 */
public class MyClassVisitor extends ClassAdapter implements Opcodes {

    public MyClassVisitor(ClassVisitor cv){
        super(cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        // Base类中有两个方法：无参构造以及process方法，这里不增强构造方法
        if (!name.equals("<init>") && mv != null) {
            mv = new MyMethodVisitor(mv);
        }
        return mv;
    }

    class MyMethodVisitor extends MethodAdapter implements Opcodes {

        public MyMethodVisitor(MethodVisitor mv){
            super(mv);
        }

        /**
         * <pre>
         *     有方法被调用时进入该方法.
         *     将AOP中的前置逻辑就放在这里
         * </pre>
         */
        @Override
        public void visitCode() {
            super.visitCode();
            // visitXXXInsn:  实现字节码的插入.
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("start");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }

        /**
         * <pre>
         *     当ASM访问到无参数指令时进入该方法.
         *     return 指令前, 将AOP中的后置逻辑放在这里
         * </pre>
         * @param opcode
         */
        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
                // 方法在返回之前，打印"end"
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("end");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            }
            mv.visitInsn(opcode);
        }
    }
}
