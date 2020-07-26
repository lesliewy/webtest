package cn.jdk.concurrent.atomic.p1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 * 内部有一 volatile int, 可以通过set修改.
 * 自旋 + CAS:    compareAndSet 判断当前值和预期值是否相等.
 * get():
 * set():
 * boolean compareAndSet(int expect, int u):
 * getAndIncrement(): 返回旧值.
 * incrementAndGet(): 返回新值.
 *
 * sun.misc.Unsafe:
 * 获取类实例中变量的偏移量: valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
   基于偏移量对值进行操作:  unsafe.compareAndSwapInt(this, valueOffset, expect, update);
 * </pre>
 *
 * Created by leslie on 2019/11/20.
 */
public class AtomicIntegerDemo {

    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
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
        System.out.println(i);
    }
}
