package cn.jdk.concurrent.deamon.p1;

/**
 * <pre>
 *     守护线程在被迫退出(因没有用户线程导致的退出)的时候并不会执行 finnaly 块中的代码
 * </pre>
 * 
 * Created by leslie on 2020/6/28.
 */
public class ThreadTest4 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
        // Thread.sleep(2000);
        System.out.println("主线程执行完毕");
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
