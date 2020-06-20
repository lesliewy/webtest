package cn.frequent.singleton.destroy;

import java.lang.reflect.Constructor;

/**
 * Created by leslie on 2020/6/19.
 */
public class DestroyByReflection {

    /**
     * <pre>
     * 通过反射破坏单例
     * 通过反射把它的构造函数设成可访问的，然后去生成一个新的对象
     * </pre>
     */
    public static void main(String[] args) throws Exception {
        /**
         * 验证单例有效性
         */
        Elvis elvis1 = Elvis.INSTANCE;
        Elvis elvis2 = Elvis.INSTANCE;
        // 方法区上的类变量指向堆，和栈上的对象引用一样.
        Elvis.INSTANCE.leaveTheBuilding();
        System.out.println("elvis1 == elvis2 ? ===>" + (elvis1 == elvis2));
        System.err.println("-----------------");
        /**
         * 反射调用构造方法
         */
        Class clazz = Elvis.class;
        Constructor cons = clazz.getDeclaredConstructor(null);
        cons.setAccessible(true);
        Elvis elvis3 = (Elvis) cons.newInstance(null);
        System.out.println("elvis1 == elvis3 ? ===> " + (elvis1 == elvis3));
    }
}
