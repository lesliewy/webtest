package cn.frequent.singleton.destroy;

import java.io.Serializable;

/**
 * Created by leslie on 2020/6/21.
 */
public class SerializableElvisImprove1 implements Serializable {

    public static final SerializableElvisImprove1 INSTANCE = new SerializableElvisImprove1();

    private SerializableElvisImprove1(){
        System.err.println("Elvis Constructor is invoked!");
    }

    /**
     * 反序列化时会以反射方式调用此方法来生成对象.
     * @return
     */
    private Object readResolve() {
        return INSTANCE;
    }
}
