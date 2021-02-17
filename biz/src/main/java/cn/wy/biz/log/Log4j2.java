package cn.wy.biz.log;

import java.net.URL;
import java.net.URLClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 如果要使用日志框架:
 *    1: maven引入jar;
 *    2: xml or properties 放在classpath里.
 *
 * biz module 中 main 方式启动, classpath 包含:
 *    1, jdk 中的jar;
 *    2, maven中的jar;
 *    3, biz/target/class,  dal/target/class,  即 biz dal 中的resource
 * </pre>
 */
public class Log4j2 {

    private static Logger logger;

    public static void main(String[] args) {
        // @Todo 这种方式指定configuration不行, 不知道为什么？？？ 暂时是把 myweb/resources/log4j2.xml 放到 biz/resources/
//         PropertyConfigurator.configure("/Users/leslie/MyProjects/GitHub/webtest/myweb/src/main/resources/log4j2.xml");
        logger = LoggerFactory.getLogger(Log4j2.class);
        test1();
    }

    private static void test1() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }

        logger.info(Log4j2.class.getName());
        logger.info("logback 成功了");
        logger.error("logback 成功了");
        logger.debug("logback 成功了");
    }
}
