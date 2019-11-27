package cn.jdk.concurrent.cyclicbarrier.p1;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier(parties, runnableCommand)
 *    每调用一次await(), parties - 1，直至0，开始执行 runnableCommand.
 *    dowait(): 如果某一个thread中断，该thread抛出InterruptException, 同时会通知其他等待的线程，其他线程抛出BrokenBarrierException;
 *              当count降至0时，发生tripped, 在最后一个线程上会执行runnableCommand, 如果runnableCommand执行失败, 所有waiter都会BrokenBarrierException;
 *              发生tripped时, count 会被重置, 恢复到parties, 等待下次tripped(nextGeneration());
 *
 * CountDownLatch允许一个或多个线程等待一组事件的产生，而CyclicBarrier用于等待其他线程运行到栅栏位置.
 *     CountDownLatch 是主线程等待其他所有线程都完成，再开始；
 *     CyclicBarrier 是跟主线程没有关系，是等所有线程到达后，再一起执行所有的线程.
 *
 * Created by leslie on 2019/11/17.
 */
public class CyclicBarrierTest {

    // 自定义工作线程
    private static class Worker extends Thread {

        private CyclicBarrier cyclicBarrier;

        public Worker(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            super.run();

            try {
                System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始执行");
                // 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

        for (int i = 0; i < threadCount; i++) {
            System.out.println("创建工作线程" + i);
            Worker worker = new Worker(cyclicBarrier);
            worker.start();
        }
    }
}
