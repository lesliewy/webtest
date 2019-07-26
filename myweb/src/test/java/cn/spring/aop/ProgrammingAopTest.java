package cn.spring.aop;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import com.wy.spring.aop.programming.SimpleBeforeAdvice;
import com.wy.spring.aop.programming.SimpleMethodInterceptor;
import com.wy.spring.aop.xml.UserService;

/**
 * Created by leslie on 2018/10/22.
 */
public class ProgrammingAopTest {

    @Test
    public void test1() {
        // 被代理的类
        UserService userService = new UserService();
        // 创建 Proxy 的工厂类
        ProxyFactory proxyFactory = new ProxyFactory(userService);
        // 代理类中要运用的 Advice
        SimpleBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        // 代理类中要运用的 MethodInterceptor
        SimpleMethodInterceptor simpleMethodInterceptor = new SimpleMethodInterceptor();
        // 给 proxyFactory 添加 Advice
        proxyFactory.addAdvice(simpleBeforeAdvice);
        proxyFactory.addAdvice(simpleMethodInterceptor);
        // 通过 proxyFactory 创建 代理类
        UserService nKK = (UserService) proxyFactory.getProxy();
        // 执行代理类的方法
        nKK.showUserInfo();
    }
}
