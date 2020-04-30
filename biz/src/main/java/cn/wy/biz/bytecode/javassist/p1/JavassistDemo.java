package cn.wy.biz.bytecode.javassist.p1;

import java.io.IOException;

import cn.wy.biz.bytecode.asm.p3.Base;
import javassist.*;

/**
 * Created by leslie on 2020/4/30.
 */
public class JavassistDemo {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException,
                                           InstantiationException, IOException {
        // 如果jvm先加载了一次Base类, 后面的 toClass 就会报错: attempted  duplicate class definition
        Base firstLoad = new Base();
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("cn.wy.biz.bytecode.asm.p3.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"this is javassistDemo start\"); }");
        m.insertAfter("{ System.out.println(\"this is javassistDemo end\"); }");
        Class c = cc.toClass();
        // cc.writeFile(System.getProperty("user.dir") + "/biz/target/classes/");
        // 这样也可以.
        cc.writeFile("/Users/leslie/Temp1/2020/0429");
        Base h = (Base) c.newInstance();
        h.process();
    }
}
