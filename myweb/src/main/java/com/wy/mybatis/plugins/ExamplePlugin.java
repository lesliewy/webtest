package com.wy.mybatis.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

/**
 * Created by leslie on 2020/7/7.
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class ExamplePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("this is ExamplePlugin intercept...");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("this is ExamplePlugin plugin...");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
