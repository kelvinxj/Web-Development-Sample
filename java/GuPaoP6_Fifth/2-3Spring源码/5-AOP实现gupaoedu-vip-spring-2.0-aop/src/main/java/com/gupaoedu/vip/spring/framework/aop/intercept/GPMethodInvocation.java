package com.gupaoedu.vip.spring.framework.aop.intercept;

import com.gupaoedu.vip.spring.framework.aop.aspect.GPJoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom.
 */
public class GPMethodInvocation implements GPJoinPoint {

    protected final Object proxy;

    protected final Object target;

    protected final Method method;

    protected Object[] arguments = new Object[0];

    private final Class<?> targetClass;

    private Map<String, Object> userAttributes = new HashMap<String, Object>();

    protected final List<?> interceptorsAndDynamicMethodMatchers;

    private int currentInterceptorIndex = -1;

    public GPMethodInvocation(
            Object proxy, Object target, Method method, Object[] arguments,
            Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {

        this.proxy = proxy;
        this.target = target;
        this.targetClass = targetClass;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    public Object proceed() throws Throwable{
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return this.method.invoke(target, this.arguments);
        }
        Object interceptorOrInterceptionAdvice =
                this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);

        if (interceptorOrInterceptionAdvice instanceof GPMethodInterceptor) {
            GPMethodInterceptor mi = (GPMethodInterceptor) interceptorOrInterceptionAdvice;
            return mi.invoke(this);
        }else {
            return proceed();
        }
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public void setUserAttribute(String key, Object value) {
        this.userAttributes.put(key,value);
    }

    @Override
    public Object getUserAttribute(String key) {
        return this.userAttributes.get(key);
    }
}
