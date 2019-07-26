package com.wy.spring.aop.programming;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AOP中的 aroud
 * Created by leslie on 2018/10/22.
 */
public class SimpleMethodInterceptor implements MethodInterceptor {

    private static final Logger logger = LogManager.getLogger(SimpleMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("SimpleMethodInterceptor1");
        invocation.proceed();
        logger.info("SimpleMethodInterceptor2");
        return "SimpleBeforeAdvice1";
    }
}
