package com.wy.mybatis.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leslie on 2020/7/7.
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class ExamplePlugin implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExamplePlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("this is ExamplePlugin intercept...");
        return invocation.proceed();
    }

    /**
     * <pre>
     *     生成代理对象,并返回.
     *     该代理对象implements InvocationHandler.
     * </pre>
     * 
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("this is ExamplePlugin plugin...");
        logger.info("this is ExamplePlugin plugin...");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
