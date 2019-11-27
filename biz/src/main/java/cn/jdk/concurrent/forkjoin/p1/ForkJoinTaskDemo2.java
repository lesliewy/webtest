package cn.jdk.concurrent.forkjoin.p1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by leslie on 2019/11/19.
 */
public class ForkJoinTaskDemo2 {

    private class SumTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 20;

        private int              arr[];
        private int              start;
        private int              end;

        public SumTask(int[] arr, int start, int end){
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        /**
         * 小计
         */
        private Integer subtotal() {
            Integer sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            System.out.println(Thread.currentThread().getName() + ": ∑(" + start + "~" + end + ")=" + sum);
            return sum;
        }

        @Override
        protected Integer compute() {

            if ((end - start) <= THRESHOLD) {
                return subtotal();
            } else {
                int middle = (start + end) / 2;
                SumTask left = new SumTask(arr, start, middle);
                SumTask right = new SumTask(arr, middle, end);
                // 如果是ForkJoinWorkerThread运行过程中fork()，则直接加入到它的工作队列workQueue中，否则，重新提交任务
                left.fork();
                right.fork();

                // 当任务完成的时候返回计算结果, 会循环检查，等待结果.
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> result = pool.submit(new ForkJoinTaskDemo2().new SumTask(arr, 0, arr.length));
        // 开始执行任务，如果必要，等待计算完成
        System.out.println("最终计算结果: " + result.invoke());
        pool.shutdown();
    }
}
