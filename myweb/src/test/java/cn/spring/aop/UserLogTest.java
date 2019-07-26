package cn.spring.aop;

import com.wy.spring.aop.xml.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by leslie on 2018/10/19.
 */
public class UserLogTest {

    @Test
    public void testShowUserInfo() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        UserService service = (UserService)ctx.getBean("userService");
        service.showUserInfo();

        ctx.destroy();

    }

    @Test
    public void testShowUserInfoAnnotation(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/aop/annotationaop.xml");

        UserService service = (UserService)ctx.getBean("userService");
        service.showUserInfo(3);

        ctx.destroy();
    }

}
