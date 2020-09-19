package cn.jdk.reflect.jdkproxy.p1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * <pre>
 *
 * 使用JDK proxy 步骤:
 * 1, 通过实现 InvocationHandler 接口创建自己的调用处理器；
   2, 通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
   3, 通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
   4, 通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。
   其中的2,3,4步都封装在了Proxy.newProxyInstance()中.
 * </pre>
 * 
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
