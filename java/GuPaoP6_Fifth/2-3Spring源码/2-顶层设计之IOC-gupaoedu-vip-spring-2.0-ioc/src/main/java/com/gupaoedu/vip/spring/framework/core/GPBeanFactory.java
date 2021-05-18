package com.gupaoedu.vip.spring.framework.core;

/*
 * 创建对象工厂的最顶层的接口
 */
public interface GPBeanFactory {

    Object getBean(Class beanClass);

    Object getBean(String beanName);

}
