package cn.jdk.util.spi.p1;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <pre>
 *     jdk 原生SPI:
 *        1, 编写接口、实现类;
 *        2, classpath: META-INF/services/接口全限定名:  内容为该接口的实现类全限定名.
 *        3, ServiceLoader.load() 加载.
 *     可以动态改变一个接口的实现类.
 *
 *     问题:
 *       1，实现 SayFranceNameImpl, 光修改修改META-INF/services不行，还需要包中有实现类;  --  dubbo spi 通过javassist动态字节码技术生成.
 *       2, 每次都要for循环, 然后if判断指定的实例.
 *       2, spring ioc 也可实现;  -- dubbo spi 不强依赖任何第三方
 * </pre>
 * 
 * Created by leslie on 2020/12/16.
 */
public class SpiTest1 {

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        System.out.println("========test1=======");
        ServiceLoader<ISayName> loaders = ServiceLoader.load(ISayName.class);
        for (ISayName sayName : loaders) {
            sayName.say();
        }
    }

    public static void test2() {
        System.out.println("========test2=======");
        ServiceLoader<ISayName> loaders = ServiceLoader.load(ISayName.class);
        Iterator<ISayName> iter = loaders.iterator();
        while (iter.hasNext()) {
            ISayName sayName = iter.next();
            if (sayName instanceof SayChineseName) {
                System.out.println("this is SayChineseName");
            }
            if (sayName instanceof SayEnglishName) {
                System.out.println("this is SayEnglishName");
            }
        }
    }
}
