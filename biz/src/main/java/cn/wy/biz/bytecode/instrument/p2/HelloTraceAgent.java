package cn.wy.biz.bytecode.instrument.p2;

import java.lang.management.ManagementFactory;

/**
 * Created by leslie on 2020/4/30.
 */
public class HelloTraceAgent {

    public static void main(String[] args) throws InterruptedException {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];
        // 打印当前Pid
        System.out.println("pid:" + s);

        HelloTraceAgent helloTraceAgent = new HelloTraceAgent();
        while (true) {
            helloTraceAgent.sayHi("xunche");
            Thread.sleep(100);
        }
    }

    public String sayHi(String name) throws InterruptedException {
        sleep();
        String hi = "hi, " + name + ", " + System.currentTimeMillis();
        System.out.println(hi);
        return hi;
    }

    public void sleep() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 200));
    }
}
