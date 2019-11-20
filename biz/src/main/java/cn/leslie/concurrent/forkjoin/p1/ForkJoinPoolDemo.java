package cn.leslie.concurrent.forkjoin.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 思想和MapReduce很像（input --> split --> map --> reduce --> output）
 * 它的模型大致是这样的：线程池中的每个线程都有自己的工作队列（PS：这一点和ThreadPoolExecutor不同，ThreadPoolExecutor是所有线程公用一个工作队列，
 * 所有线程都从这个工作队列中取任务），当自己队列中的任务都完成以后，会从其它线程的工作队列中偷一个任务执行，这样可以充分利用资源
 *
 * Created by leslie on 2019/11/19.
 */
public class ForkJoinPoolDemo {

    class SendMsgTask extends RecursiveAction {

        private final int    THRESHOLD = 10;

        private int          start;
        private int          end;
        private List<String> list;

        public SendMsgTask(int start, int end, List<String> list){
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected void compute() {

            if ((end - start) <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + list.get(i));
                }
            } else {
                int middle = (start + end) / 2;
                invokeAll(new SendMsgTask(start, middle, list), new SendMsgTask(middle, end, list));
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            list.add(String.valueOf(i + 1));
        }

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ForkJoinPoolDemo().new SendMsgTask(0, list.size(), list));
        pool.awaitTermination(10, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
