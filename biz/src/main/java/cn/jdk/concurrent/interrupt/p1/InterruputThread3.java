package cn.jdk.concurrent.interrupt.p1;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 一种是当线程处于阻塞状态或者试图执行一个阻塞操作时，我们可以使用实例方法interrupt()进行线程中断，
 * 执行中断操作后将会抛出interruptException异常(该异常必须捕捉无法向外抛出)并将中断状态复位；
 *
 * 另外一种是当线程处于运行状态时，我们也可调用实例方法interrupt()进行线程中断，但同时必须手动判断中断状态，
 * 并编写中断线程的代码(其实就是结束run方法体的代码)。有时我们在编码时可能需要兼顾以上两种情况，那么就可以如下编写
 * </pre>
 *
 * Created by leslie on 2019/11/16.
 */
public class InterruputThread3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {

            @Override
            public void run() {
                try {
                    // 判断当前线程是否已中断,注意interrupted方法是静态的,执行后会对中断状态进行复位
                    // 如果没有sleep()阻塞方法，将不会抛出异常。 这种情况，可以手工检查中断状态来判断是否收到了中断请求。
                    while (!Thread.interrupted()) {
                        System.out.println("服务中...");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("中断");
                    // 中断状态被复位
                    System.out.println("interrupt:" + this.isInterrupted());

                    // 如果不知如何处理，可以恢复中断状态。
                    Thread.currentThread().interrupt();
                    System.out.println("interrupt:" + this.isInterrupted());
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
    }
}
