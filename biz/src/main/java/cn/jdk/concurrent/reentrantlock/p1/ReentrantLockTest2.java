package cn.jdk.concurrent.reentrantlock.p1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by leslie on 2019/11/16.
 */
public class ReentrantLockTest2 {

    /**
     * <pre>
     * 默认是非公平锁。 大部分情况下我们使用非公平锁，因为其性能比公平锁好很多。但是公平锁能够避免线程饥饿，某些情况下也很有用;
     * fairsync: tryAcquire中需要先看等待队列中前面有没有其他等待线程，只有在没有的情况下才CAS.
     * nonfairsync 中所有等待线程都参与state 的CAS竞争, 将其从0设置为1;
     * </pre>
     */
    static Lock lock = new ReentrantLock(true);
    // static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo(i)).start();
        }

    }

    static class ThreadDemo implements Runnable {

        Integer id;

        public ThreadDemo(Integer id){
            this.id = id;
        }

        @Override

        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 2; i++) {
                lock.lock();
                System.out.println("获得锁的线程：" + id);
                lock.unlock();
            }
        }
    }
}
