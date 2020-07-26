package cn.jdk.reflect.jdkproxy.p1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by leslie on 2020/7/7.
 */
public class App {

    public static void main(String[] args) {
        People people = new Student();
        InvocationHandler handler = new WorkHandler(people);

        // Proxy类生成的代理对象可以调用work方法并且返回真实的代理对象，也可以通过反射来对真实的代理对象进行操作
        People proxy = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(),
                                                       people.getClass().getInterfaces(), handler);
        People p = proxy.work("写代码").work("开会").work("上课");

        System.out.println("打印返回的对象");
        System.out.println(p.getClass());

        String time = proxy.time();
        System.out.println(time);
    }
}
