package cn.jdk.concurrent.yield;

/**
 * 暂停当前正在执行的线程对象，并执行其他线程
 * yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果
 * yield 不能保证使得当前正在运行的线程迅速转换到可运行的状态
 *
 * Created by leslie on 2019/11/17.
 */
public class YiledTest implements Runnable {

    private String name;

    public YiledTest(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name + ":" + i);
            if ("t1".equals(name) && i == 5) {
                System.out.println(name + ":" + i + "......yield.............");
                Thread.yield();
            }
        }
    }

    /**
     * 暂停当前正在执行的线程对象，并执行其他线程。 * * @param args * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new YiledTest("t1"));
        Thread t2 = new Thread(new YiledTest("t2"));
        t1.start();
        t2.start();
    }
}
