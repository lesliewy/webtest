package cn.leslie.concurrent.interrupt.p1;

import java.util.concurrent.TimeUnit;

/**
 * Created by leslie on 2019/11/16.
 */
public class InterruputThread2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {

            @Override
            public void run() {
                while (true) {
                    // 判断当前线程是否被中断
                    if (this.isInterrupted()) {
                        System.out.println("线程中断");
                        break;
                    }
                }

                System.out.println("已跳出循环,线程中断!");
                System.out.println("interrupt:" + this.isInterrupted());
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
    }

}
