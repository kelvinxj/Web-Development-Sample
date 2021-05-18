package com.gupaoedu.vip.spring.framework.aop.aspect;

import com.gupaoedu.vip.spring.framework.aop.intercept.GPMethodInterceptor;
import com.gupaoedu.vip.spring.framework.aop.intercept.GPMethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by Tom.
 */
public class GPAfterReturningAdviceInterceptor extends GPAbstractAspectJAdvice implements GPMethodInterceptor {

    private GPJoinPoint jp;
    public GPAfterReturningAdviceInterceptor(Object aspect, Method adviceMethod) {
        super(aspect, adviceMethod);
    }


    private void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable{
        this.invokeAdviceMethod(this.jp,returnValue,null);
    }

    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {
        jp = mi;
        Object retVal = mi.proceed();
        this.afterReturning(retVal, mi.getMethod(), mi.getArguments(), mi.getThis());
        return retVal;
    }
}
