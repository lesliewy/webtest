package cn.jdk.concurrent.deamon.p1;

/**
 * 不能再deamon 线程上执行业务操作，因为其终止条件是所有的用户线程不存在, 退出时机不可控.
 *
 * Created by leslie on 2019/11/20.
 */
public class ThreadTest2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Thread2a());
        thread1.setDaemon(true);
        thread1.start();
        Thread.sleep(10);
        System.out.println("用户线程退出");
    }
}

class Thread2a implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("1+1=" + (1 + 1));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
