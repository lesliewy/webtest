package cn.frequent.singleton.p6_register;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by leslie on 2020/9/15.
 */
public class Main {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int n = i;
            new Thread(() -> {
                System.out.println("线程" + n + "准备就绪");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(RegistSingleton.getInstance("singletonpattern.regist.ClassA"));
            }).start();
        }
    }
}
