package cn.jdk.concurrent.reentrantreadwritelock.p1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *     读写锁的可重入性.
 * </pre>
 * Created by leslie on 2019/12/12.
 */
public class ReentrantReadWriteLock1 {

    public static void main(String[] args) throws InterruptedException {
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.writeLock().lock();
                System.out.println("Thread real execute");
                lock.writeLock().unlock();
            }
        });

        // 主线程两次获取锁.
        lock.writeLock().lock();
        lock.writeLock().lock();
        // 子线程获取锁需要等待。
        t.start();
        Thread.sleep(200);

        System.out.println("release one once");
        lock.writeLock().unlock();
    }
}
