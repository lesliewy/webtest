package cn.frequent.singleton.destroy;

import java.io.Serializable;

/**
 * Created by leslie on 2020/6/19.
 */
public class SerializableElvis implements Serializable {

    public static final SerializableElvis INSTANCE = new SerializableElvis();

    private SerializableElvis(){
        System.err.println("Elvis Constructor is invoked!");
    }
}
