package com.gupaoedu.vip.spring.framework.beans.support;

import com.gupaoedu.vip.spring.framework.beans.config.GPBeanDefinition;
import com.gupaoedu.vip.spring.framework.core.GPBeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPDefaultListableBeanFactory implements GPBeanFactory {

    public Map<String,GPBeanDefinition> beanDefinitionMap = new HashMap<String,GPBeanDefinition>();

    @Override
    public Object getBean(Class beanClass) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    public void doRegistBeanDefinition(List<GPBeanDefinition> beanDefinitions) throws Exception {
        for (GPBeanDefinition beanDefinition : beanDefinitions) {
            if(this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + " is exists!!!");
            }
            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);

        }
    }
}
