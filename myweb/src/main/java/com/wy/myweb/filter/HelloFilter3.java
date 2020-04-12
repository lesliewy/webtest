package com.wy.myweb.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *     @WebFilter 方式无需再在web.xml中配置filter.
 *     servlet 3.0 规范允许(web.xml 中 version="3.1")
 * </pre>
 * Created by leslie on 2020/4/12.
 */
@WebFilter(filterName = "HelloFilter3", urlPatterns = "/*", initParams = { @WebInitParam(name = "user", value = "helloFilter3's user") })
public class HelloFilter3 implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HelloFilter3.class);
    private String              user;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        logger.info("this is hellofilter3...  requestURI: {}, user: {}", httpServletRequest.getRequestURI(), user);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.user = filterConfig.getInitParameter("user");
    }

    @Override
    public void destroy(){

    }


    public void setUser(String user) {
        this.user = user;
    }
}
