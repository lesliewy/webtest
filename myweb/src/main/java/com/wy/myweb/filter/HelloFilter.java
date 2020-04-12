package com.wy.myweb.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <pre>
 *     实现filter 的几种方式:
 *       1, implements Filter;
 *       2, extends OncePerRequestFilter, GenericFilterBean
 *       3, 注解 WebFilter
 *
 *     如果implements Filter 会报错: AbstractMethodError  原因是 Filter 所在的servlet-api.jar 是provided, maven 引入的
 *     已经支持interface 的default 方法, 而tomcat 的lib/ 下的jar不支持，仍需实现 init(), destroy(), 在filter中实现该方法即可。
 *
 * </pre>
 * Created by leslie on 2020/4/2.
 */
public class HelloFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HelloFilter.class);

    /**
     * 这种方式实现的filter, 初始化变量不需要什么特殊操作，父类已经处理好，提供setter，然后直接使用即可.
     */
    private String              user;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("this is hellofilter...  requestURI: {}, user: {}", httpServletRequest.getRequestURI(), user);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    public void setUser(String user) {
        this.user = user;
    }
}
