package cn.jdk.concurrent.threadpoolexecutor.p1;

import java.util.concurrent.*;

/**
 * 线程池状态:
 * RUNNING：这个没什么好说的，这是最正常的状态：接受新的任务，处理等待队列中的任务；
 * SHUTDOWN：不接受新的任务提交，但是会继续处理等待队列中的任务；
 * STOP：不接受新的任务提交，不再处理等待队列中的任务，中断正在执行任务的线程；
 * TIDYING：所有的任务都销毁了，workCount 为 0。线程池的状态在转换为 TIDYING 状态时，会执行钩子方法 terminated()；
 * TERMINATED：terminated() 方法结束后，线程池的状态就会变成这个；
 * RUNNING 定义为 -1，SHUTDOWN 定义为 0，其他的都比 0 大，所以等于 0 的时候不能提交任务，大于 0 的话，连正在执行的任务也需要中断。
 * RUNNING -> SHUTDOWN：当调用了 shutdown() 后，会发生这个状态转换，这也是最重要的；
 * (RUNNING or SHUTDOWN) -> STOP：当调用shutdownNow() 后，会发生这个状态转换，这下要清楚 shutDown() 和 shutDownNow() 的区别了；
 * SHUTDOWN -> TIDYING：当任务队列和线程池都清空后，会由 SHUTDOWN 转换为 TIDYING；
 * STOP -> TIDYING：当任务队列清空后，发生这个转换； TIDYING -> TERMINATED：这个前面说了，当 terminated() 方法结束后； Created by leslie on
 * 2019/11/19.
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        // 使用Executors方式创建
        // 它的特点在于工作线程数目被限制为 1，操作一个无界的工作队列，所以它保证了所有任务的都是被顺序执行，最多会有一个任务处于活动状态，并且不允许使用者改动线程池实例，因此可以避免其改变线程数目。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // 它是一种用来处理大量短时间工作任务的线程池，具有几个鲜明特点：它会试图缓存线程并重用，当无缓存线程可用时，就会创建新的工作线程；如果线程闲置的时间超过 60
        // 秒，则被终止并移出缓存；长时间闲置时，这种线程池，不会消耗什么资源。其内部使用 SynchronousQueue 作为工作队列
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 重用指定数目（nThreads）的线程，其背后使用的是无界的工作队列，任何时候最多有 nThreads
        // 个工作线程是活动的。这意味着，如果任务数量超过了活动队列数目，将在工作队列中等待空闲线程出现；如果有工作线程退出，将会有新的工作线程被创建，以补足指定的数目 nThreads
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        // 创建单线程池，返回 ScheduledExecutorService，可以进行定时或周期性的工作调度。
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        // 和newSingleThreadScheduledExecutor()类似，创建的是个 ScheduledExecutorService，可以进行定时或周期性的工作调度，区别在于单一工作线程还是多个工作线程
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        // 这是一个经常被人忽略的线程池，Java 8 才加入这个创建方法，其内部会构建ForkJoinPool，利用Work-Stealing算法，并行地处理任务，不保证处理顺序。
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        // 是最原始的线程池创建，上面1-3创建方式都是对ThreadPoolExecutor的封装
        ThreadPoolExecutor tp = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.SECONDS,
                                                       new LinkedBlockingQueue<Runnable>());

        // 如果Executors提供的三个静态方法能满足要求，就尽量使用它提供的三个方法，因为自己去手动配置ThreadPoolExecutor的参数有点麻烦，要根据实际任务的类型和数量来进行配置
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 13, 200, TimeUnit.MILLISECONDS,
                                                             new ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 18; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);// 提交线程
            System.out.println("线程池中的线程数目:" + executor.getPoolSize() + "队列中等待执行的任务数目:" + executor.getQueue().size()
                               + "已经执行完的任务数目:" + executor.getCompletedTaskCount());
        }

        /**
         * submit: 可以执行带返回值的Future, 当然也可以执行Runnable.
         */
        Future<Long> result = cachedThreadPool.submit(new Callable<Long>() {

            @Override
            public Long call() throws Exception {
                return System.currentTimeMillis();
            }
        });
        try {
            System.out.println("运行结果：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 会执行等待队列中的任务，但是不接收新任务.
        executor.shutdown();// 启动有序关闭,
    }
}

class MyTask implements Runnable {

    private int taskName;

    public MyTask(int num){
        this.taskName = num;
    }

    public void run() {
        System.out.println("正在执行:" + taskName);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task" + taskName + "执行完毕");
    }

}
