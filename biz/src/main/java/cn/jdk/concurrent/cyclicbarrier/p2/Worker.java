package cn.jdk.concurrent.cyclicbarrier.p2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by leslie on 2019/11/17.
 */
public class Worker implements Runnable {

    private CyclicBarrier                       barrier;
    private int                                 workerId;
    private ConcurrentHashMap<Integer, Integer> concurrentHashMap;

    public Worker(CyclicBarrier barrier, int workerId, ConcurrentHashMap<Integer, Integer> concurrentHashMap){
        this.barrier = barrier;
        this.workerId = workerId;
        this.concurrentHashMap = concurrentHashMap;
    }

    @Override
    public void run() {
        try {
            int sleepTime = new Random().nextInt(3000);
            System.out.println(workerId + " is working...");
            Thread.sleep(sleepTime);
            concurrentHashMap.put(workerId, sleepTime);
            barrier.await();
            System.out.println(workerId + "with time " + sleepTime + " finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
