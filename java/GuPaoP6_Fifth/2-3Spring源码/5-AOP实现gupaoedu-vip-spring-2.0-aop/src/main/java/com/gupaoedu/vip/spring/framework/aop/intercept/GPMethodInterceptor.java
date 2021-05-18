package com.gupaoedu.vip.spring.framework.aop.intercept;

/**
 * Created by Tom.
 */
public interface GPMethodInterceptor {

    Object invoke(GPMethodInvocation invocation) throws Throwable;

}
