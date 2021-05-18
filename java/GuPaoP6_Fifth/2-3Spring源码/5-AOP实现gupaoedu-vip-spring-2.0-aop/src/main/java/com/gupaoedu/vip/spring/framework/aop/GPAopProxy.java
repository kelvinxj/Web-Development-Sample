package com.gupaoedu.vip.spring.framework.aop;

/**
 * Created by Tom.
 */
public interface GPAopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
