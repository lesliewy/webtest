package cn.jdk.concurrent.semaphore.p1;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <pre>
 * 信号量是一个非负整数，表示了当前公共资源的可用数目.
 * acquire（获取） 和 release（释放）。
 * 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 *
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制，限流.
 * </pre>
 * 
 * Created by leslie on 2019/11/19.
 */
public class SemaphoreTest {

    /**
     * <pre>
     * Semaphore有两个构造函数，参数permits表示许可数，它最后传递给了AQS的state值。线程在运行时首先获取许可，如果成功，许可数就减1，线程运行，
     * 当线程运行结束就释放许可，许可数就加1。如果许可数为0，则获取失败，线程位于AQS的等待队列中，它会被其它释放许可的线程唤醒。
     *
     * 在创建Semaphore对象的时候还可以指定它的公平性。
     * 一般常用非公平的信号量， 非公平信号量是指在获取许可时先尝试获取许可，而不必关心是否已有需要获取许可的线程位于等待队列中，如果获取失败，才会入列。
     * 而公平的信号量在获取许可时首先要查看等待队列中是否已有线程，如果有则入列
     * </pre>
     */
    private Semaphore smp = new Semaphore(3);
    private Random    rnd = new Random();

    class TaskDemo implements Runnable {

        private String id;

        TaskDemo(String id){
            this.id = id;
        }

        @Override
        public void run() {
            try {
                smp.acquire();
                System.out.println("Thread " + id + " is working");
                Thread.sleep(rnd.nextInt(1000));
                smp.release();
                System.out.println("Thread " + id + " is over");
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        SemaphoreTest semaphoreDemo = new SemaphoreTest();
        // 注意我创建的线程池类型，
        ExecutorService se = Executors.newCachedThreadPool();
        se.submit(semaphoreDemo.new TaskDemo("a"));
        se.submit(semaphoreDemo.new TaskDemo("b"));
        se.submit(semaphoreDemo.new TaskDemo("c"));
        se.submit(semaphoreDemo.new TaskDemo("d"));
        se.submit(semaphoreDemo.new TaskDemo("e"));
        se.submit(semaphoreDemo.new TaskDemo("f"));
        se.shutdown();
    }
}
