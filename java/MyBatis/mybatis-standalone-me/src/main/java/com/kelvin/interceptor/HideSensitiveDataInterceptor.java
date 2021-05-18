package com.kelvin.interceptor;

import com.kelvin.entity.User;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;

@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class
                })
        }
)
public class HideSensitiveDataInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        ArrayList<User> userList = (ArrayList<User>) result;
        for(User u : userList){
            u.setName("***Protected***");
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

//    @Override
//    public void setProperties(Properties properties) {
//        Interceptor.super.setProperties(properties);
//    }
}
