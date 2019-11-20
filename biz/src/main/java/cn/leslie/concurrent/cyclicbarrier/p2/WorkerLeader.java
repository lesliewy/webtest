package cn.leslie.concurrent.cyclicbarrier.p2;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by leslie on 2019/11/17.
 */
public class WorkerLeader implements Runnable {

    private ConcurrentHashMap<Integer, Integer> concurrentHashMap;
    private final List<Integer>                 workerIdList;

    public WorkerLeader(ConcurrentHashMap<Integer, Integer> concurrentHashMap, List<Integer> workerIdList){
        this.concurrentHashMap = concurrentHashMap;
        this.workerIdList = workerIdList;
    }

    @Override
    public void run() {
        /**
         * workerLeader执行实在worker执行之后，因为CyclicBarrier构造函数的第二个参数代表着栅栏中的线程
         * 都到达后(这里的到达其实就是说最后一个线程调用barrier的await之后)，才会执行的runnable。
         */
        System.out.println("=====last batch is ok ......");
        for (Integer workerId : workerIdList) {
            System.out.println("workerId is:" + workerId + " ,executeTime is:" + concurrentHashMap.get(workerId));
        }
    }

}
