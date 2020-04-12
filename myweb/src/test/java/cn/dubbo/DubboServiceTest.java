package cn.dubbo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.wy.biz.dubbo.DubboService;

/**
 * Created by leslie on 2020/2/25.
 */
public class DubboServiceTest {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("dubbo/dubbo.xml");
        classPathXmlApplicationContext.start();

        DubboService d1 = (DubboService) classPathXmlApplicationContext.getBean("d1");
        String a = d1.getName();
        System.out.println(a);
    }
}
