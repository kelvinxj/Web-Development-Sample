package com.gupaoedu.demo02;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class GpImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * This class gives us a register of Ioc container.
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        //1. include bean class to RootBeenDefinition object.
        RootBeanDefinition cache = new RootBeanDefinition(CacheService.class);
        registry.registerBeanDefinition("cache",cache);
    }
}
