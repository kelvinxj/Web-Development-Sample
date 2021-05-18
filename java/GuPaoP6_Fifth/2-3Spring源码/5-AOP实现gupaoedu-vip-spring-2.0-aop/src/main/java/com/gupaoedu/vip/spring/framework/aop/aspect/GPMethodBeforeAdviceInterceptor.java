package com.gupaoedu.vip.spring.framework.aop.aspect;

import com.gupaoedu.vip.spring.framework.aop.intercept.GPMethodInterceptor;
import com.gupaoedu.vip.spring.framework.aop.intercept.GPMethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by Tom.
 */
public class GPMethodBeforeAdviceInterceptor extends GPAbstractAspectJAdvice implements GPMethodInterceptor {

    private GPJoinPoint jp;
    public GPMethodBeforeAdviceInterceptor(Object aspect, Method adviceMethod) {
        super(aspect, adviceMethod);
    }

    public void before(Method method, Object[] arguments, Object aThis) throws Throwable{
        invokeAdviceMethod(this.jp,null,null);
    }

    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {
        jp = mi;
        this.before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
