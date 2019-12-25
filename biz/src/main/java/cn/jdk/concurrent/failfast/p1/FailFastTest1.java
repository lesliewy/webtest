package cn.jdk.concurrent.failfast.p1;

import java.util.*;

/**
 * <pre>
 *     集合的迭代器通过内部类实现。 该内部类持有 expectedModCount, 默认等于外部类的modCount。迭代器调用next()时，首先会检查两值是否相等.
 * </pre>
 * 
 * Created by leslie on 2019/12/17.
 */
public class FailFastTest1 {

    public static void main(String[] args) {
        // test1();
        test2();
    }

    /**
     * 单线程版本. ConcurrentModificationException
     */
    public static void test1() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i == 3) {
                list.remove(3);
            }
            System.out.println(iterator.next());
            i++;
        }
    }

    public static void test2() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i + "", i + "");
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i == 3) {
                map.remove(3 + "");
            }
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            i++;
        }
    }
}
