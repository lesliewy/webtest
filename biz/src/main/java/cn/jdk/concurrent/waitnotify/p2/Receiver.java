package cn.jdk.concurrent.waitnotify.p2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by leslie on 2019/11/16.
 */
public class Receiver implements Runnable {

    private Data load;

    public Receiver(Data data){
        this.load = data;
    }

    @Override
    public void run() {
        for (String receivedMessage = load.receive(); !"End".equals(receivedMessage); receivedMessage = load.receive()) {

            System.out.println(receivedMessage);

            // ...
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(("Thread interrupted" + e));
            }
        }
    }
}
