package com.gupaoedu.vip.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * 自定义的拦截器
 * @Signature 注解就可以表示一个方法签名， 唯一确定一个方法
 */
@Intercepts({
        @Signature(
                type = Executor.class // 需要拦截的类型
                ,method = "query"     // 需要拦截的方法
                // args 中指定 被拦截方法的 参数列表
                ,args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = Executor.class
                ,method = "close"
                ,args = {boolean.class}
        )
})
public class FirstInterceptor implements Interceptor {
    private int testProp;

    /**
     * 执行拦截逻辑的方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("FirtInterceptor  拦截之前 ....");
        Object obj = invocation.proceed();
        System.out.println("FirtInterceptor  拦截之后 ....");
        return obj;
    }

    /**
     * 决定是否触发 intercept方法
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("---->"+properties.get("testProp"));
    }

    public int getTestProp() {
        return testProp;
    }

    public void setTestProp(int testProp) {
        this.testProp = testProp;
    }
}
