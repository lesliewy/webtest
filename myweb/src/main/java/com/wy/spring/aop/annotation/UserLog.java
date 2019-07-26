package com.wy.spring.aop.annotation;

import com.wy.spring.aop.xml.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by leslie on 2018/10/19.
 */
@Aspect
@Service
public class UserLog {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    // 配置切点 及要传的参数
    @Pointcut("execution(* com.wy.spring.aop.xml.UserService.showUserInfo(..)) && args(id)")
    public void myPointCut(int id) {

    }

    // 配置连接点 方法开始执行时通知
    @Before("myPointCut(id)")
    public void beforeLog(int id) {
        logger.info("开始执行前置通知  日志记录:" + id);
    }

    // 方法执行完后通知
    @After("myPointCut(id)")
    public void afterLog(int id) {
        logger.info("开始执行后置通知 日志记录:" + id);
    }

    // 执行成功后通知
    @AfterReturning("myPointCut(id)")
    public void afterReturningLog(int id) {
        logger.info("方法成功执行后通知 日志记录:" + id);
    }

    // 抛出异常后通知
    @AfterThrowing("myPointCut(id)")
    public void afterThrowingLog(int id) {
        logger.info("方法抛出异常后执行通知 日志记录" + id);
    }

    // 环绕通知
    @Around("myPointCut(id)")
    public Object aroundLog(ProceedingJoinPoint joinpoint, int id) {
        Object result = null;
        try {
            logger.info("环绕通知开始 日志记录" + id);
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
