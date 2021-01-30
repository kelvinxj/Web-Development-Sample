package com.gupaoedu.ConFig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@ConfigurationProperties
public class FileWebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/MyFiles/**").addResourceLocations("file:C:/MySourceGithub/MySource/java/Spring/springboot_demo_manual_create/MyFiles");
        super.addResourceHandlers(registry);
    }
}
