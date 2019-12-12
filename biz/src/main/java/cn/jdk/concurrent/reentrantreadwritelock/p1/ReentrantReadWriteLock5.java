package cn.jdk.concurrent.reentrantreadwritelock.p1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *     ReentrantReadWriteLock 版本.
 *     耗时约 100 ms.
 * </pre>
 * 
 * Created by leslie on 2019/12/12.
 */
public class ReentrantReadWriteLock5 {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void get(Thread thread) {

        lock.readLock().lock();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        lock.readLock().unlock();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                get(Thread.currentThread());
            }
        }).start();
    }
}
