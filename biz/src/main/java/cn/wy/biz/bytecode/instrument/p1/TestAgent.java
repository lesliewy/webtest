package cn.wy.biz.bytecode.instrument.p1;

import java.lang.instrument.Instrumentation;

/**
 * <pre>

 * </pre>
 * Created by leslie on 2020/4/30.
 */
public class TestAgent {

    public static void agentmain(String args, Instrumentation inst) {
        // 指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new TestTransformer(), true);
        try {
            // 重定义类并载入新的字节码, 这里指定的class 需和 TestTransformer 中的保持一致.
            inst.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
