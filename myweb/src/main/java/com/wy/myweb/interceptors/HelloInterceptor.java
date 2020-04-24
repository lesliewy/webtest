package com.wy.myweb.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by leslie on 2020/4/13.
 */
public class HelloInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HelloInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        logger.info("this is HelloInterceptor...");
        String name = request.getParameter("name");
        if ("wang".equals(name)) {
            logger.info("wang, forbidden");
            return false;
        }
        return true; // 放行
    }
}
