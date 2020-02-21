package cn.jdk.concurrent.reentrantlock.p1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * 当使用synchronized实现锁时,阻塞在锁上的线程除非获得锁否则将一直等待下去，也就是说这种无限等待获取锁的行为无法被中断.
 * ReentrantLock的lockInterruptibly()可实现线程的中断: 等待队列中无限循环时，相比不带中断的lock()的逻辑多了判断线程是否被中断的检查(doAcquireSharedInterruptibly)
 * 如果是，则该等待线程直接抛出InterruptedException;
 * </pre>
 * Created by leslie on 2019/11/16.
 */
public class ReentrantLockTest3 {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new ThreadDemo(lock1, lock2));// 该线程先获取锁1,再获取锁2
        Thread thread1 = new Thread(new ThreadDemo(lock2, lock1));// 该线程先获取锁2,再获取锁1
        thread.start();
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(50);
        thread.interrupt();// 是第一个线程中断
    }

    static class ThreadDemo implements Runnable {

        Lock firstLock;
        Lock secondLock;

        public ThreadDemo(Lock firstLock, Lock secondLock){
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            try {
                firstLock.lockInterruptibly();
                TimeUnit.MILLISECONDS.sleep(10);// 更好的触发死锁
                secondLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName() + "正常结束!");
            }
        }
    }
}
