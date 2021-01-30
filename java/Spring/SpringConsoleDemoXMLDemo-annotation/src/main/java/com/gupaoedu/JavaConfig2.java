package com.gupaoedu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages={"com.gupaoedu"})
@PropertySource("classpath:spring-db.properties")
//@ComponentScan(value={"com.gupaoedu.controller"}, useDefaultFilters = false
//        , includeFilters = {@ComponentScan.Filter(Controller.class)})

/**
 * if not specify scan path(basePackages), default is to scan current and sub directories
 */
public class JavaConfig2 {

}
