package cn.leslie.concurrent.atomic.p1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 让普通变量也享受原子操作
 *
 * Updater只能修改它可见范围内的变量。因为Updater使用反射得到这个变量。如果变量不可见，就会出错。比如如果score申明为private，就是不可行的。
 *
 * 为了确保变量被正确的读取，它必须是volatile类型的。如果我们原有代码中未申明这个类型，那么简单得申明一下就行，这不会引起什么问题。
 *
 * 由于CAS操作会通过对象实例中的偏移量直接进行赋值，因此，它不支持static字段（Unsafe.objectFieldOffset()不支持静态变量）。
 *
 * Created by leslie on 2019/11/20.
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static class Candidate {

        int          id;
        // 如果直接把int改成atomicinteger, 可能对代码破坏比较大
        // 因此使用AtomicIntegerFieldUpdater对score进行封装
        volatile int score;
    }

    // 通过反射实现
    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class,
                                                                                                                 "score");
    // 检查Updater是否工作正确, allScore的结果应该跟score一致
    public static AtomicInteger                              allScore     = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread() {

                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            t[i].start();
        }
        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }

        System.out.println("score=" + stu.score);
        System.out.println("allScore=" + allScore);
    }
}
