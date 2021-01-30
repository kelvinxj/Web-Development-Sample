package com.gupaoedu.demo02;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(GpImportSelector.class)
@Import(GpImportBeanDefinitionRegistrar.class)
public @interface EnableGpDefine {
}
