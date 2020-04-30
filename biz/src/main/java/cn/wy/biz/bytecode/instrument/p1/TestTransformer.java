package cn.wy.biz.bytecode.instrument.p1;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * <pre>
 *     使用jvm 提供的 instrument, 实现类的运行时重载入.
 *
 *     类文件转换器
 *     transform()方法会在类文件被加载时调用, 在其中利用 asm 或者 javassist 来修改字节码.
 * </pre>
 * 
 * Created by leslie on 2020/4/30.
 */
public class TestTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("Transforming " + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("cn.wy.biz.bytecode.instrument.p1.Base");
            CtMethod m = cc.getDeclaredMethod("process");
            m.insertBefore("{ System.out.println(\"this is in instrument. start\"); }");
            m.insertAfter("{ System.out.println(\"this is in instrument. end\"); }");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
