package cn.jdk.reflect.jdkproxy.p1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by leslie on 2020/7/7.
 */
public class WorkHandler implements InvocationHandler {

    private Object obj;

    public WorkHandler(Object obj){
        this.obj = obj;
    }

    /**
     * proxy 是真实的代理对象。invoke方法可以返回调用代理对象方法的返回结果，也可以返回对象的真实代理对象
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before 动态代理...");
        System.out.println(proxy.getClass().getName());
        System.out.println(this.obj.getClass().getName());
        if (method.getName().equals("work")) {
            method.invoke(this.obj, args);
            System.out.println("after 动态代理...");
            return proxy;
        } else {
            System.out.println("after 动态代理...");
            return method.invoke(this.obj, args);
        }
    }
}
