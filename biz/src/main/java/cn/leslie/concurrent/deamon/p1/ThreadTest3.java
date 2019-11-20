package cn.leslie.concurrent.deamon.p1;

/**
 * Created by leslie on 2019/11/20.
 */
public class ThreadTest3 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Thread3a());
        thread.setDaemon(true);
        thread.start();
        System.out.println("Thread是否时daemon线程" + thread.isDaemon());
        Thread.sleep(100);
        System.out.println("用户线程退出");
    }
}

class Thread3a implements Runnable {

    @Override
    public void run() {
        // deamon 线程创建的子线程仍然是deamon线程.
        Thread3b thread1 = new Thread3b();
        thread1.start();
        System.out.println("Thread1是否是daemon线程" + thread1.isDaemon());
    }
}

class Thread3b extends Thread {

    public void run() {
        System.out.println("dosomething");
    }
}
