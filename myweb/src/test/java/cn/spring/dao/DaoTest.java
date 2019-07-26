package cn.spring.dao;

import org.junit.BeforeClass;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by leslie on 2018/5/13.
 */
public class DaoTest {

    protected static ApplicationContext ctx;

    @BeforeClass
    public static void beforeClass() {
        try {
            ctx = new ClassPathXmlApplicationContext("spring/datasource/spring-jdbc2.xml",
                                                     "spring/transaction/srping-tx-proxy-3.xml");
        } catch (BeansException e) {
            System.out.println("before class exception: " + e);
        }
    }
}
