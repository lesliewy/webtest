package cn.leslie.concurrent.waitnotify.p2;

/**
 * Created by leslie on 2019/11/16.
 */
public class DataTest {
    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();
    }
}
