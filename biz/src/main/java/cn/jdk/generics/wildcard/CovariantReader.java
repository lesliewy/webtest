package cn.jdk.generics.wildcard;

import java.util.List;

/**
 * Created by leslie on 2020/11/5.
 */
public class CovariantReader<T> {

    public T readCovariant(List<? extends T> list) {
        return list.get(0);
    }


}
