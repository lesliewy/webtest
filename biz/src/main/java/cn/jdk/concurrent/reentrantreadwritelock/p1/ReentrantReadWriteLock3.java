package cn.jdk.concurrent.reentrantreadwritelock.p1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *     锁降级
 *     从写锁降级成读锁，并不会自动释放当前线程获取的写锁，仍然需要显示的释放，否则别的线程永远也获取不到写锁
 *     当有写锁时，除了获得写锁的这个线程可以获得读锁外，其他线程不能获得读锁
 * </pre>
 * 
 * Created by leslie on 2019/12/12.
 */
public class ReentrantReadWriteLock3 {

    public static void main(String[] args) {
        ReentrantReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.writeLock().lock();
        System.out.println("writeLock");

        rtLock.readLock().lock();
        System.out.println("get read lock");
    }
}
