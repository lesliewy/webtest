package cn.jdk.concurrent.atomic.p1;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 与AtomicInteger类似, 只是里面封装了一个对象, 而不是int, 对引用进行修改.
 * Created by leslie on 2019/11/20.
 */
public class AtomicReferenceTest {

    public final static AtomicReference<String> attxnicStr = new AtomicReference<String>("abc");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {

                public void run() {
                    try {
                        Thread.sleep(Math.abs((int) (Math.random() * 100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (attxnicStr.compareAndSet("abc", "def")) {
                        System.out.println("Thread:" + Thread.currentThread().getId() + " change value to "
                                           + attxnicStr.get());
                    } else {
                        System.out.println("Thread:" + Thread.currentThread().getId() + " change failed!");
                    }
                }
            }.start();
        }
    }
}
