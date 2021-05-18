package com.gupaoedu.vip.spring.framework.aop;

import com.gupaoedu.vip.spring.framework.aop.support.GPAdvisedSupport;

/**
 * Created by Tom.
 */
public class GPDefaultAopProxyFactory {
    public GPAopProxy createAopProxy(GPAdvisedSupport config) throws Exception {
        Class targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0){
            return new GPJdkDynamicAopProxy(config);
        }
        return new GPCglibAopPorxy();
    }
}
