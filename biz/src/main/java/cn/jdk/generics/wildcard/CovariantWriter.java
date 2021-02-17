package cn.jdk.generics.wildcard;

import java.util.List;

/**
 * Created by leslie on 2020/11/5.
 */
public class CovariantWriter<T> {

    public <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    public <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }
}
