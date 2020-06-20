package cn.frequent.singleton.p1_lazy;

/**
 * <pre>
 *     懒汉，线程不安全
 * </pre>
 * Created by leslie on 2020/6/19.
 */
public class Singleton1 {

    private static Singleton1 instance;

    private Singleton1(){
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
