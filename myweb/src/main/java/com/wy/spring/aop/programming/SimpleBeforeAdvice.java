package com.wy.spring.aop.programming;

import com.wy.spring.aop.xml.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by leslie on 2018/10/22.
 */
public class SimpleBeforeAdvice implements MethodBeforeAdvice {

    private static final Logger logger = LogManager.getLogger(SimpleBeforeAdvice.class);

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        logger.info("Currently Processing " + method);
    }
}
