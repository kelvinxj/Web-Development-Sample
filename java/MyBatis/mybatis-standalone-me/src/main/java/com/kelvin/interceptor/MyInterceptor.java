package com.kelvin.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class
                }),
                @Signature(type = Executor.class, method = "close", args = {boolean.class})
        }
)
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("Before interceptor");
        Object result = invocation.proceed();
        System.out.println("After interceptor");
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
//
    @Override
    public void setProperties(Properties properties) {
        System.out.println("---->" + properties.getProperty("testProp"));
    }
}
