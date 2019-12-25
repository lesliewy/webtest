package cn.jdk.concurrent.failfast.p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 *     避免方法:
 *      1, 如果要进行remove操作，可以调用迭代器的remove方法而不是集合类的remove方法.
 *          只能remove当前遍历过的那个元素，所以调用该方法并不会发生fail-fast现象.
 *      2, 使用其他类.
 *           对于ArrayList, 读写分离, 这样就避免操作同一份数据. CopyOnWriteArrayList.
 *           对于Map, 也可以读写分离，参考CopyOnWriteArrayList 实现CopyOnWriteMap(jdk未提供)
 *           对于Map, 还可以使用ConcurrentHashMap.
 *               当iterator被创建后集合再发生改变就不再是抛出ConcurrentModificationException，取而代之的是在改变时new新的数据从而不影响原有的数据 ，
 *               iterator完成后再将头指针替换为新的数据 ，这样iterator线程可以使用原来老的数据，而写线程也可以并发的完成改变。即迭代不会发生fail-fast，但不保证获取的是最新的数据。
 * </pre>
 * 
 * Created by leslie on 2019/12/17.
 */
public class FailFastTest2 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i == 3) {
                iterator.remove(); // 迭代器的remove()方法
            }
            System.out.println(iterator.next());
            i++;
        }
    }
}
