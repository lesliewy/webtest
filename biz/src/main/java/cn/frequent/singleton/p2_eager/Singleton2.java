package cn.frequent.singleton.p2_eager;

/**
 * <pre>
 *     跟Singleton1差不多。都是在类初始化阶段，只不过一个是类变量，一个是静态代码块。
 * </pre>
 * 
 * Created by leslie on 2020/6/19.
 */
public class Singleton2 {

    private static Singleton2 instance = null;
    static {
        instance = new Singleton2();
    }

    private Singleton2(){
    }

    public static Singleton2 getInstance() {
        return instance;
    }
}
