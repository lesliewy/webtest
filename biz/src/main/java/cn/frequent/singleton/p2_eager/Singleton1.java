package cn.frequent.singleton.p2_eager;

/**
 * <pre>
 *     饿汉
 *   利用CLassload加载类的线程安全特性。
 * </pre>
 * Created by leslie on 2020/6/19.
 */
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    private Singleton1(){
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
