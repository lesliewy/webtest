package cn.jdk.concurrent.copyonwrite.p1;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     JDK 未提供 CopyOnWriteMap,  模仿CopyOnWriteArrayList实现.
 * </pre>
 * 
 * Created by leslie on 2019/12/17.
 */
public abstract class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {

    private volatile Map<K, V> internalMap;

    public CopyOnWriteMap(){
        internalMap = new HashMap<K, V>();
    }

    public V put(K key, V value) {

        synchronized (this) {
            Map<K, V> newMap = new HashMap<K, V>(internalMap);
            V val = newMap.put(key, value);
            internalMap = newMap;
            return val;
        }
    }

    public V get(Object key) {
        return internalMap.get(key);
    }

    public void putAll(Map<? extends K, ? extends V> newData) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<K, V>(internalMap);
            newMap.putAll(newData);
            internalMap = newMap;
        }
    }
}
