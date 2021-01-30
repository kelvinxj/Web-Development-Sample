package com.gupaoedu.pojo.demo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import sun.misc.Cache;

public class GpImportSelector implements ImportSelector {
    @Override
    /**
     * parameter is the bean class name String
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{LoggerService.class.getName(), CacheService.class.getName()};
    }
}
