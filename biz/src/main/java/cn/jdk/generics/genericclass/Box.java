package cn.jdk.generics.genericclass;

/**
 * <pre>
 *     泛型类.
 * </pre>
 * 
 * Created by leslie on 2020/11/5.
 */
public class Box<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
