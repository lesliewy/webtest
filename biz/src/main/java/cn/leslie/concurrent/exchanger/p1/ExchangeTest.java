package cn.leslie.concurrent.exchanger.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 用于线程间数据的交换，线程会阻塞在Exchanger的exchange方法上，直到另外一个线程也执行到同一个Exchanger的exchange方法，
 * 二者进行数据的交换，然后彼此各自执行各自的任务.
 *
 * 只用于2个线程之间的交换， 超过2个，结果随机。
 *
 * Created by leslie on 2019/11/19.
 */
public class ExchangeTest {

    public static void main(String[] args) {
        final Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
        new Thread() {

            @Override
            public void run() {
                List<Integer> l = new ArrayList<Integer>(2);
                l.add(1);
                l.add(2);
                try {
                    /**
                     * 当一个线程到达 exchange 调用点时，如果它的伙伴线程此前已经调用了此方法，那么它的伙伴会被调度唤醒并与之进行对象交换，然后各自返回。
                     * 如果它的伙伴还没到达交换点，那么当前线程将会被挂起，直至伙伴线程到达——完成交换正常返回；
                     * 或者当前线程被中断——抛出中断异常；又或者是等候超时——抛出超时异常
                     */
                    l = exchanger.exchange(l);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                System.out.println("thread1:" + Thread.currentThread().getName() + ":" + l);
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                List<Integer> l = new ArrayList<Integer>(2);
                l.add(4);
                l.add(5);
                try {
                    l = exchanger.exchange(l);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                System.out.println("thread2:" + Thread.currentThread().getName() + ":" + l);
            }
        }.start();

    }
}
