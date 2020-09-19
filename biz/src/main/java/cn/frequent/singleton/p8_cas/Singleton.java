package cn.frequent.singleton.p8_cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <pre>
 * CAS 是线程安全的,使用了无锁编程
 *
 * Created by leslie on 2020/9/15.
 * </pre>
 */
public class Singleton {

    /** 利用AtomicReference */
    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference<Singleton>();

    /**
     * 私有化
     */
    private Singleton(){
    }

    /**
     * 用CAS确保线程安全
     */
    public static final Singleton getInstance() {
        for (;;) {
            Singleton current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new Singleton();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}
