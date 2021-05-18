package com.gupaoedu.vip.spring.framework.core;

/**
 * Created by Tom.
 */
public interface GPBeanFactory {

    Object getBean(String beanName);

    Object getBean(Class beanClass);
}
