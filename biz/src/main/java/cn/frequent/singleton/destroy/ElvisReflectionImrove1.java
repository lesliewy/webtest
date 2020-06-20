package cn.frequent.singleton.destroy;

/**
 * <pre>
 *     饿汉方式防反射破坏改进
 *     private 构造方法中抛异常。
 * </pre>
 *
 * Created by leslie on 2020/6/19.
 */
public class ElvisReflectionImrove1 {

    public static final ElvisReflectionImrove1 INSTANCE = new ElvisReflectionImrove1();

    private ElvisReflectionImrove1(){
        System.err.println("Elvis Constructor is invoked!");
        if (INSTANCE != null) {
            System.err.println("实例已存在，无法初始化！");
            throw new UnsupportedOperationException("实例已存在，无法初始化！");
        }
    }
}
