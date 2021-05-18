package com.gupaoedu.vip.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * Created by Tom.
 */
public interface GPJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key,Object value);

    Object getUserAttribute(String key);

}
