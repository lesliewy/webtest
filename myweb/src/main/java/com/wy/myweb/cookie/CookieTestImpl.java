package com.wy.myweb.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wy.myweb.object.RestResult;

/**
 * <pre>
 *  服务端返回了cookie 后，如果设置了domain, path, 浏览器访问服务器时只会将domain, path 都匹配的cookie 发往服务器.
 *  如果设置的domain= .leslie.com  访问 wy1.leslie.com  wy2.leslie.com 都会带上该cookie;
 *  如果没有设置domain, path, cookie 返回浏览器后，浏览器会设置默认值. domain即域名, path 即url中path,   wy1.leslie.com/cookie/c1  domain=wy1.leslie.com  path=/cookie
 *  Created by leslie on 2020/2/11
 * </pre>
 */
@RestController
public class CookieTestImpl {

    private static final Logger logger = LoggerFactory.getLogger(CookieTestImpl.class);

    @RequestMapping(method = RequestMethod.GET, value = "/cookie/c1")
    public RestResult setCookie1(HttpServletResponse response) {
        logger.info("this is cookie test: /cookie/c1");
        // 创建一个 cookie
        Cookie cookie = new Cookie("username", "wy1");
        cookie.setDomain("wy1.leslie.com");
        cookie.setPath("/c1/abc");
        // 设置 cookie过期时间
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        // 添加到 response 中
        response.addCookie(cookie);

        RestResult result = new RestResult();
        result.setName("jjjjj");
        return result;
    }

    /**
     * 非注解方式获取cookie中对应的key值
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/cookie/c2", method = RequestMethod.GET)
    public String getCookies(HttpServletRequest request) {
        // HttpServletRequest 装请求信息类
        // HttpServletRespionse 装相应信息的类
        // Cookie cookie=new Cookie("sessionId","CookieTestInfo");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                logger.info("name: {}, domain: {}, value: {}", cookie.getName(), cookie.getDomain(), cookie.getValue());
            }
        }

        return null;
    }
}
