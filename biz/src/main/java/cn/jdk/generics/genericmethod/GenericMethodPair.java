package cn.jdk.generics.genericmethod;

/**
 * Created by leslie on 2020/11/5.
 */
public class GenericMethodPair<K, V> {

    private K key;

    private V value;

    public GenericMethodPair(K key, V value){

        this.key = key;

        this.value = value;

    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
