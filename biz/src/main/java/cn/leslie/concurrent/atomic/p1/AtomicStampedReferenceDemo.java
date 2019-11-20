package cn.leslie.concurrent.atomic.p1;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 封装了一个引用, 主要解决ABA问题: 线程一准备用CAS将变量的值由A替换为B, 在此之前线程二将变量的值由A替换为C, 线程三又将C替换为A, 然后线程一执行CAS时发现变量的值仍然为A, 所以线程一CAS成功.
 boolean compareAndSet(V expectedReference,V newReference,int expectedStamp,int newStamp): 比较设置 参数依次为：期望值 写入新值 期望时间戳 新时间戳
 public V getReference(): 获得当前对象引用
 public int getStamp():获得当前时间戳
 public void set(V newReference, int newStamp):设置当前对象引用和时间戳
 *
 * Created by leslie on 2019/11/20.
 */
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {
        // 模拟多个线程同时更新后台数据库，为用户充值
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();
            new Thread() {

                public void run() {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
                                    System.out.println("余额小于20元，充值成功，余额:" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                // System.out.println("余额大于20元，无需充值");
                                break;
                            }
                        }
                    }

            }.start();
        }

        // 用户消费线程，模拟消费行为
        new Thread() {

            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
                                System.out.println("成功消费10元，余额:" + money.getReference());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }
}
