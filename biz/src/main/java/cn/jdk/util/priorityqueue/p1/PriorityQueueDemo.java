package cn.jdk.util.priorityqueue.p1;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 使用的是Object[]来存储, 堆.
 * 插入: 从下往上堆化;
 * 出队: 从上往下堆化. 将最后一个元素移至堆顶，再堆化，避免出现空洞.
 *
 * iterator 并不会有序输出。
 *
 * PriorityQueue优先级规则可以由我们根据具体需求而定制， 方式有2种：
 * 1,添加元素自身实现了Comparable接口，确保元素是可排序的对象
 * 2,如果添加元素没有实现Comparable接口，可以在创建PriorityQueue队列时直接指定比较器。
 *
 * Created by leslie on 2019/11/23.
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<String>();
        // 入列
        q.offer("1");
        q.offer("2");
        q.offer("5");
        q.offer("3");
        q.offer("4");

        /**
         * iterator 并不会有序输出，只将 object[] 中元素从前往后输出.
         */
        System.out.println("iterator output:");
        Iterator<String> iter = q.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            System.out.println(s);
        }
        System.out.println("iterator output finish.");
        // 出列
        System.out.println(q.poll()); // 1
        System.out.println(q.poll()); // 2
        System.out.println(q.poll()); // 3
        System.out.println(q.poll()); // 4
        System.out.println(q.poll()); // 5

    }
}
