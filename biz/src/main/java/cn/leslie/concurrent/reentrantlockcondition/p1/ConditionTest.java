package cn.leslie.concurrent.reentrantlockcondition.p1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition接口在使用前必须先调用ReentrantLock的lock()方法获得锁, await()将释放锁,并且在该Condition上等待,直到有其他线程调用Condition的signal()方法唤醒线程
 *
 * Thread.currentThread() != getExclusiveOwnerThread()   tryRelease()中的，正是有此判断，才需要先lock(), 否则抛出: IllegalMonitorStateException, 正如使用wait(), notify() 前必须synchronized一样.
 * Created by leslie on 2019/11/16.
 */
public class ConditionTest {

    static ReentrantLock lock      = new ReentrantLock();
    static Condition     condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        lock.lock();
        new Thread(new SignalThread()).start();
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("主线程等待通知");
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
        System.out.println("主线程恢复运行");
    }

    static class SignalThread implements Runnable {

        @Override
        public void run() {
            System.out.println("子线程在等待....");
            lock.lock();
            try {
                condition.signal();
                System.out.println("子线程通知");
            } finally {
                lock.unlock();
            }
        }
    }
}
