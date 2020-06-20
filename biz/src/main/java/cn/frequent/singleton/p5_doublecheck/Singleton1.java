package cn.frequent.singleton.p5_doublecheck;

/**
 * <pre>
 *     双重校验.
 *
 *     "一般的情况下，我会使用第三种方式，只有在要明确实现lazy loading效果时才会使用第五种方式，另外，如果涉及到反序列化创建对象时我会试着使用枚举的方式来实现单例"
 * </pre>
 * 
 * Created by leslie on 2020/6/19.
 */
public class Singleton1 {

    /**
     * <pre>
     * 必须要有volatile.
     * 没有情况下: 线程1执行到了 new Elvis(); 细分为: 分配内存; 解析, 会将对象引用赋值给singleton变量; 初始化;
     * 线程2调用，singleton 可能已经指向了新对象，但该对象还没有进行初始化。 这样线程2就拿到了一个半成品。
     *
     * ??? volatile 并不能保证new Elvis()的原子性，应该还是可能拿到半成品呀？
     * </pre>
     **/
    private volatile static Singleton1 singleton;

    private Singleton1(){
    }

    public static Singleton1 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton1.class) {
                if (singleton == null) {
                    singleton = new Singleton1();
                }
            }
        }
        return singleton;
    }
}
