package cn.spring.retry;

import com.wy.spring.retry.RetryService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Created by leslie on 2018/10/18.
 */
@Configuration
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RetryServiceTest {

    @Bean
    public RetryService retryService() {
        return new RetryService();
    }

    public static void main(String[] args) throws Exception {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RetryServiceTest.class);
        final RetryService retryService = applicationContext.getBean(RetryService.class);
        retryService.retryTest();
    }
}
