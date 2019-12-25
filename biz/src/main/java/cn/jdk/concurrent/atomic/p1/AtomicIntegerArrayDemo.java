package cn.jdk.concurrent.atomic.p1;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * <pre>
 * 无锁的数组
 *
 public final int get(int i): 获得数组第i个下标的元素
 public final int length(): 获得数组的长度
 public final int getAndSet(int i, int newValue): 将数组第i个下标设置为newValue，并返回旧的值
 public final boolean compareAndSet(int i, int expect, int update): 进行CAS操作，如果第i个下标的元素等于expect，则设置为update，设置成功返回true
 public final int getAndIncrement(int i): 将第i个下标的元素加1
 public final int getAndDecrement(int i): 将第i个下标的元素减1
 public final int getAndAdd(int i, int delta): 将第i个下标的元素增加delta（delta可以是负数）
 * </pre>
 * 
 * Created by leslie on 2019/11/20.
 */
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {

        public void run() {
            for (int k = 0; k < 10000; k++) {
                arr.incrementAndGet(k % arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }
        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }
        System.out.println(arr);
    }
}
