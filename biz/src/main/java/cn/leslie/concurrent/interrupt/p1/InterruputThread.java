package cn.leslie.concurrent.interrupt.p1;

import java.util.concurrent.TimeUnit;

/**
 * Created by leslie on 2019/11/16.
 */
public class InterruputThread {

    /**
     * 处于运行期且非阻塞的状态的线程，这种情况下，直接调用Thread.interrupt()中断线程是不会得到任响应的
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {

            @Override
            public void run() {
                while (true) {
                    System.out.println("未被中断");
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
    }
}
