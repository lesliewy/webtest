package cn.frequent.singleton.destroy;

/**
 * <pre>
 * 枚举方式单例.
 *   线程安全:
 *   可序列化:
 *   防反射: 无法通过反射创建枚举对象。(Constructor类的newInstance)
 *   防反序列化: readEnum()， 通过 Enum.valueOf()得到枚举常量。
 *
 * </pre>
 *
 *
 * Created by leslie on 2020/6/21.
 */
public enum ElvisEnum {
                       INSTANCE;

    public void leaveTheBuilding() {
    }
}
