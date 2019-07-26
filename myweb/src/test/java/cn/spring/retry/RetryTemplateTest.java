package cn.spring.retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.TimeoutRetryPolicy;

/**
 * Created by leslie on 2018/10/18.
 */
public class RetryTemplateTest {

    private static final Logger logger = LogManager.getLogger(RetryTemplateTest.class);

    @Test
    public void test1() throws Exception {
        org.springframework.retry.support.RetryTemplate template = new org.springframework.retry.support.RetryTemplate();

        TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
        policy.setTimeout(3000L);

        template.setRetryPolicy(policy);

        String result = template.execute(new RetryCallback<String, Exception>() {

            public String doWithRetry(RetryContext context) {
                // Do stuff that might fail, e.g. webservice operation
                logger.info("this is doWithRetry...");
                return "aaa";
            }

        });
        logger.info("result: " + result);
    }
}
