package cn.frequent.singleton.p6_register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *  登记式单例实际上维护的是一组单例类的实例，将这些实例存储到一个Map(登记簿)中，对于已经登记过的单例，则从工厂直接返回，
 *  对于没有登记的，则先登记，而后返回
 *
 *  登记式单例并没有去改变类，他所做的就是起到一个登记的作用
 *  Spring 中使用.
 *
 * Created by leslie on 2020/9/15.
 * </pre>
 *
 */
public class RegistSingleton {

    // 用ConcurrentHashMap来维护映射关系，这是线程安全的
    public static final Map<String, Object> REGIST = new ConcurrentHashMap<String, Object>();
    static {
        // 把RegistSingleton自己也纳入容器管理
        RegistSingleton registSingleton = new RegistSingleton();
        REGIST.put(registSingleton.getClass().getName(), registSingleton);
    }

    private RegistSingleton(){
    }

    public static Object getInstance(String className) {
        // 如果传入的类名为空，就返回RegistSingleton实例
        if (className == null) className = RegistSingleton.class.getName();
        // 如果没有登记就用反射new一个
        if (!REGIST.containsKey(className)) {
            // 没有登记就进入同步块
            synchronized (RegistSingleton.class) {
                // 再次检测是否登记
                if (!REGIST.containsKey(className)) {
                    try {
                        // 实例化对象
                        REGIST.put(className, Class.forName(className).newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // 返回单例
        return REGIST.get(className);
    }
}
