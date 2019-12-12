package cn.jdk.concurrent.reentrantreadwritelock.p1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *     同一线程:
 *     锁降级：从写锁变成读锁；  支持.
 *     锁升级：从读锁变成写锁;   不支持。
 * </pre>
 * Created by leslie on 2019/12/12.
 */
public class ReentrantReadWriteLock2 {

    public static void main(String[] args) {
        ReentrantReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.readLock().lock();
        System.out.println("get readLock.");
        rtLock.writeLock().lock();
        System.out.println("blocking");
    }
}
