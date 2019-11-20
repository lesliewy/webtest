package cn.leslie.concurrent.join.p1;

/**
 * Created by leslie on 2019/11/17.
 */
public class Join1 {

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
        System.out.println("MainThread run finished.");
    }
}
