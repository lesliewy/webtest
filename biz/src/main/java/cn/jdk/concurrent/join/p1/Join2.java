package cn.jdk.concurrent.join.p1;

/**
 * join() method suspends the execution of the calling thread until the object called finishes its execution.
 * t.join()要阻塞的是调用此方法的线程, 而不是线程t阻塞;  线程t执行完，当前线程才执行;
 * 使用的是wait()来实现:
 *     join() 是synchronized 方法. 调用线程会持有子线程threadA的对象锁, 当threadA结束时，调用exit(), 在exit()(位于/hotspot/src/share/vm/runtime/thread.cpp)
 *     中有notify(), 唤醒阻塞在threadA上的线程.
 *
 * Created by leslie on 2019/11/17.
 */
public class Join2 {

    public static void main(String[] args) {
        System.out.println("MainThread run start.");

        // 启动一个子线程
        Thread threadA = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("threadA run start.");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("threadA run finished.");
            }
        });
        threadA.start();

        System.out.println("MainThread join before");
        try {
            threadA.join(); // 调用join()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MainThread run finished.");
    }
}
