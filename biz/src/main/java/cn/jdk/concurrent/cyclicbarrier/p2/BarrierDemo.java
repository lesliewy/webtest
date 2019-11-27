package cn.jdk.concurrent.cyclicbarrier.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by leslie on 2019/11/17.
 */
public class BarrierDemo {

    public static final int Worker_Count = 9;

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        List<Integer> workIds = new ArrayList<>();
        for (int i = 0; i < Worker_Count; i++) {
            workIds.add(i);
        }
        /**
         * 这里的barrier构造函数指定了两个内容一个是barrier的执行线程await的个数，另一个是当最后一个线程满足await后，要执行什么动作。 具体可以参考CycliBarrier的构造函数中的说明。
         * 这里简单介绍一下，就是说，当最后一个worker，也就是第三个worker执行await的时候，那么就会触发WorkerLeader的动作。
         * 同时由于barrier是可多次的（相比于latch）,所以可以有多个worker执行，但是WorkerLeader每次只取三个进行操作。
         * 这边使用了ConcurrentHashMap,作为多个线程间共享数据的方式，当然，也可以用future。
         */
        CyclicBarrier barrier = new CyclicBarrier(3, new WorkerLeader(concurrentHashMap, workIds));
        for (int i = 0; i < Worker_Count; i++) {
            new Thread(new Worker(barrier, i, concurrentHashMap)).start();
        }
    }
}
