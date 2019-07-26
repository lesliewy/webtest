package com.wy.spring.aop.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by leslie on 2018/10/19.
 */
public class UserLog {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    
    // 方法执行前通知
    public void beforeLog() {
        logger.info("开始执行前置通知  日志记录");
    }

    // 方法执行完后通知
    public void afterLog() {
        logger.info("开始执行后置通知 日志记录");
    }

    // 执行成功后通知
    public void afterReturningLog() {
        logger.info("方法成功执行后通知 日志记录");
    }

    // 抛出异常后通知
    public void afterThrowingLog() {
        logger.info("方法抛出异常后执行通知 日志记录");
    }

    // 环绕通知
    public Object aroundLog(ProceedingJoinPoint joinpoint) {
        Object result = null;
        try {
            logger.info("环绕通知开始 日志记录");
            long start = System.currentTimeMillis();

            // 有返回参数 则需返回值
            result = joinpoint.proceed();

            long end = System.currentTimeMillis();
            logger.info("总共执行时长" + (end - start) + " 毫秒");
            logger.info("环绕通知结束 日志记录");
        } catch (Throwable t) {
            logger.info("出现错误");
        }
        return result;
    }
}
