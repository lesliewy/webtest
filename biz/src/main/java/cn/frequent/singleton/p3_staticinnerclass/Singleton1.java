package cn.frequent.singleton.p3_staticinnerclass;

/**
 * <pre>
 *     静态内部类
 *     同样利用了classloder的机制来保证初始化instance时只有一个线程. 这里的初始化指的是 SingletonHolder 的初始化.
 *     饿汉方式是只要Singleton类被装载了，那么instance就会被实例化（没有达到lazy loading效果），而这种方式是Singleton类被装载了，instance不一定被初始化。
 *     因为SingletonHolder类没有被主动使用，只有显式通过调用getInstance方法时，才会显式装载SingletonHolder类，从而实例化instance。
 *     耗资源的初始化适用.
 * </pre>
 * 
 * Created by leslie on 2020/6/19.
 */
public class Singleton1 {

    private static class SingletonHolder {

        private static final Singleton1 INSTANCE = new Singleton1();
    }

    private Singleton1(){
    }

    public static final Singleton1 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
