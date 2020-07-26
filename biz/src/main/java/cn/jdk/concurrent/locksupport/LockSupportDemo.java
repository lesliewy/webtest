package cn.jdk.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by leslie on 2020/6/29.
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println("thread线程被唤醒");
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
