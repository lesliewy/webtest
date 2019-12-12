package cn.jdk.concurrent.deamon.p1;

/**
 * <pre>
 *
 *
 * java的线程分为两类: 用户线程和daemon线程
 * 用户线程: 用户线程可以简单的理解为用户定义的线程,当然包括main线程(以前我错误的认为main线程也是一个daemon线程,但是慢慢的发现原来main线程不是,
 * 因为如果我再main线程中创建一个用户线程,并且打出日志,我们会发现这样一个问题,main线程运行结束了,但是我们的线程任然在运行)
 * daemon线程: daemon线程是为我们创建的用户线程提供服务的线程,比如说jvm的GC等等,这样的线程有一个非常明显的特征:
 * 当用户线程运行结束的时候,daemon线程将会自动退出.(由此我们可以推出下面关于daemon线程的几条基本特点)
 *
 * daemon 线程的特点:
 *   A. 守护线程创建的过程中需要先调用setDaemon方法进行设置,然后再启动线程.否则会报出IllegalThreadStateException异常.(个人在想一个问题,为什么不能动态更改线程为daemon线程?有时间一个补上这个内容,现在给出一个猜测: 是因为jvm判断线程状态的时候,如果当前只存在一个线程Thread1,如果我们把这个线程动态更改为daemon线程,jvm会认为当前已经不存在用户线程而退出,稍后将会给出正确结论,抱歉!如果有哪位大牛看到,希望给出指点,谢谢!)
 　　B. 由于daemon线程的终止条件是当前是否存在用户线程,所以我们不能指派daemon线程来进行一些业务操作,而只能服务用户线程.
 　　C. daemon线程创建的子线程任然是daemon线程.
 * </pre>
 * 
 * Created by leslie on 2019/11/20.
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Thread2());
        thread1.start();
        // 已经start的线程不能设置deamon, 否则抛异常.
        thread1.setDaemon(true);
        Thread.sleep(10);
        System.out.println("用户线程退出");
    }
}

class Thread2 implements Runnable {

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
