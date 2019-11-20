package cn.leslie.concurrent.forkjoin.p1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask: 一个递归有结果的ForkJoinTask（有返回值）
 *
 * Created by leslie on 2019/11/19.
 */
public class RecursiveTaskDemo2 {

    private static class Fibonacci extends RecursiveTask<Integer> {

        final int n;

        public Fibonacci(int n){
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            } else {
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();
                Fibonacci f2 = new Fibonacci(n - 1);
                return f2.compute() + f1.join();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> future = pool.submit(new Fibonacci(10));
        System.out.println(future.get());
        pool.shutdown();
    }
}
