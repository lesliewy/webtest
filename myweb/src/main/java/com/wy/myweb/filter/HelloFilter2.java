package com.wy.myweb.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *     如果web.xml 中配置该filter 会报错.
 * </pre>
 * 
 * Created by leslie on 2020/4/12.
 */
public class HelloFilter2 implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HelloFilter2.class);

    /** 这种方式实现的filter， 需要借助filterConfig来获取初始化变量. */
    private String              user;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.user = filterConfig.getInitParameter("user");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        logger.info("this is hellofilter2...  requestURI: {}, user: {}", httpServletRequest.getRequestURI(), user);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void setUser(String user) {
        this.user = user;
    }
}
