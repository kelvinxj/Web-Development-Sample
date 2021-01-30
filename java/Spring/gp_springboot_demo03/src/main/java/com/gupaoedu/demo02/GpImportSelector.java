package com.gupaoedu.demo02;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class GpImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata
     * @return : return classes IoC container need to load.
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{LoggerService.class.getName()};
    }
}
