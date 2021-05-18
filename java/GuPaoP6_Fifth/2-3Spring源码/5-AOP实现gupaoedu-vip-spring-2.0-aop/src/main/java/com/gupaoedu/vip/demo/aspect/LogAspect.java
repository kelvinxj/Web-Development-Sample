package com.gupaoedu.vip.demo.aspect;

import com.gupaoedu.vip.spring.framework.aop.aspect.GPJoinPoint;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tom.
 */
@Slf4j
public class LogAspect {

    //在调用一个方法之前，执行before方法
    public void before(GPJoinPoint joinPoint){
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(),System.currentTimeMillis());
        //这个方法中的逻辑，是由我们自己写的
        log.info("Invoker Before Method!!!");
    }
    //在调用一个方法之后，执行after方法
    public void after(GPJoinPoint joinPoint){
        long startTime = (Long)joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
        long endTime = System.currentTimeMillis();
        log.info("Invoker After Method!!!" + "use time :" + (endTime - startTime));
    }

    public void afterThrowing(GPJoinPoint joinPoint,Throwable tx){

        log.info("出现异常");
    }
}
