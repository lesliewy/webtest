package com.wy.spring.retry;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by leslie on 2018/10/22.
 */
public class RetryTemplateProgramming {

    private static final Logger logger = LogManager.getLogger(RetryTemplateProgramming.class);

    public void test() {
        final RetryTemplate retryTemplate = new RetryTemplate();
        final SimpleRetryPolicy policy = new SimpleRetryPolicy(5,
                                                               Collections.<Class<? extends Throwable>, Boolean> singletonMap(Exception.class,
                                                                                                                              true));
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(1000);
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        final RetryCallback<Object, Exception> retryCallback = new RetryCallback<Object, Exception>() {

            @Override
            public Object doWithRetry(RetryContext context) throws Exception {
                logger.info("do some thing");
                // 设置context一些属性,给RecoveryCallback传递一些属性
                context.setAttribute("key1", "value1");
                logger.info(context.getRetryCount());
                throw new Exception("exception");
                // return null;
            }
        };

        // 如果RetryCallback执行出现指定异常, 并且超过最大重试次数依旧出现指定异常的话,就执行RecoveryCallback动作
        final RecoveryCallback<Object> recoveryCallback = new RecoveryCallback<Object>() {

            @Override
            public Object recover(RetryContext context) throws Exception {
                logger.info("do recory operation");
                logger.info(context.getAttribute("key1"));
                return null;
            }
        };

        try {
            final Object execute = retryTemplate.execute(retryCallback, recoveryCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
