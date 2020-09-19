package cn.frequent.singleton.p7_threadlocal;

/**
 * <pre>
 * 可以保证局部单例，即在各自的线程中是单例的，但是线程与线程之间不保证单例。
 *
 * 对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
 * 前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，即线程隔离，因此可以同时访问而互不影响
 *
 * Created by leslie on 2020/9/15.
 * </pre>
 *
 */
public class Singleton1 {

    private Singleton1(){
    }

    private static final ThreadLocal<Singleton1> threadLocal = new ThreadLocal<Singleton1>() {

        @Override
        protected Singleton1 initialValue() {
            return new Singleton1();
        }
    };

    public static Singleton1 getInstance() {
        return threadLocal.get();
    }
}
