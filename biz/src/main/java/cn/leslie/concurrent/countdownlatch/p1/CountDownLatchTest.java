package cn.leslie.concurrent.countdownlatch.p1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 中和ReentrantLock 一样, 也有一个Sync, extends AbstractQueuedSynchronizer.
 * CountDownLatch 中的是共享锁, 多个线程可以共享，而 ReentrantLock 中是独占锁，只能有一个线程占用，其他需要CAS来竞争;
 *
 * new CountDownLatch(2) 即是设置 state 为2; CountDownLatch 并不能重置 state, CyclicBarrier 可以.
 * countDown(): state - 1;
 * await(): 将当前主线程加入同步队列(acquireSharedInterruptibly), 无限循环地检查state 是否是0;
 *
 * Thread 的实例方法join 也可以阻塞当前线程，等待子线程完成，但是join 无法控制线程池里的线程执行.
 *
 * Created by leslie on 2019/11/17.
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行…… ……");
        // 第一个子线程执行
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        // 第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                latch.countDown();
            }
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕…… ……");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}
