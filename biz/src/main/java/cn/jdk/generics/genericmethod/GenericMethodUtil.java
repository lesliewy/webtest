package cn.jdk.generics.genericmethod;

/**
 * <pre>
 *     泛型方法.
 *     在返回类型前面加上一个类似<K, V>的形式
 * </pre>
 * 
 * Created by leslie on 2020/11/5.
 */
public class GenericMethodUtil {

    public static <K, V> boolean compare(GenericMethodPair<K, V> p1, GenericMethodPair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    /**
     * <pre>
     *     边界符.
     *     为了值可以比较, 告诉编译器类型参数T代表的都是实现了Comparable接口的类
     * </pre>
     * 
     * @param anArray
     * @param elem
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) {
                ++count;
            }
        }
        return count;
    }

}
