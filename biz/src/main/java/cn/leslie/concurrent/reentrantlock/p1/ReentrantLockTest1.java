package cn.leslie.concurrent.reentrantlock.p1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by leslie on 2019/11/16.
 */
public class ReentrantLockTest1 {

    /**
     * 和synchronized 一样, ReentrantLock 是 可重入的. 这得益于 AQS 中的state, 记录了重入次数, int 类型，显然其上限就是int的上限.
     * 相同点:
     *    都是独占锁,只允许线程互斥的访问临界区.
     *    都是可重入的;
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        for (int i = 1; i <= 3; i++) {
            lock.lock();
        }

        for (int i = 1; i <= 3; i++) {
            try {

            } finally {
                lock.unlock();
            }
        }
    }
}
