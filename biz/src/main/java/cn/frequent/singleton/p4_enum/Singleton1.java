package cn.frequent.singleton.p4_enum;

import cn.wy.pojo.User;

/**
 * <pre>
 * 枚举类保证只有一个实例（即使使用反射机制也无法多次实例化一个枚举量）
 *
 * Created by leslie on 2020/6/19.
 * </pre>
 *
 */
public enum Singleton1 {
                        INSTANCE;

    public void whateverMethod() {
    }

    private User user;

    // 枚举的特性,在JVM中只会被实例化一次
    Singleton1(){
        user = new User();
    }

    public User getInstance() {
        return user;
    }
}
