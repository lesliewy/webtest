package cn.frequent.singleton.p1_lazy;

/**
 * <pre>
 *     懒汉，线程安全
 *     效率很低，99%情况下不需要同步。
 * </pre>
 * Created by leslie on 2020/6/19.
 */
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2(){
    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
