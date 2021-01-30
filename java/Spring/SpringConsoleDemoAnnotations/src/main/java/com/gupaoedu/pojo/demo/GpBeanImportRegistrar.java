package com.gupaoedu.pojo.demo;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class GpBeanImportRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    /**
     * BeanDefinitionRegistry is IOC register of bean
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata
            , BeanDefinitionRegistry registry) {
        RootBeanDefinition cache = new RootBeanDefinition(CacheService.class);
        registry.registerBeanDefinition("cache1234",cache);
    }
}
