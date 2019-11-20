package cn.leslie.concurrent.waitnotify.p2;

/**
 * Created by leslie on 2019/11/16.
 */
public class Data {

    private String  packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    /**
     * 将这些方法放在synchronized方法中以提供内部锁。如果调用wait（）方法的线程不拥有固有锁，则会抛出错误
     * 
     * @param packet
     */
    public synchronized void send(String packet) {
        /**
         * 由于notify（）和notifyAll（）随机唤醒正在此对象监视器上等待的线程，因此满足条件并不总是很重要。有时可能会发生线程被唤醒，但实际上并没有满足条件。
         */
        while (!transfer) {
            try {
                // 调用 this 的 wait, 如果监视器是在某个实例对象上，就必须调用该实例对象的wait.
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(("Thread interrupted" + e));
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(("Thread interrupted" + e));
            }
        }
        transfer = true;

        notifyAll();
        return packet;
    }
}
