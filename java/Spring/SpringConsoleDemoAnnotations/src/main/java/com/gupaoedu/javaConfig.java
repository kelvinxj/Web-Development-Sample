package com.gupaoedu;


import com.gupaoedu.pojo.Student;
import com.gupaoedu.pojo.demo.GpBeanImportRegistrar;
import com.gupaoedu.pojo.demo.GpImportSelector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
//add student class as a bean explicitly.
//this is the static use of @Import
//@Import({Student.class})
//@Import({GpImportSelector.class})
@Import({GpBeanImportRegistrar.class})
public class javaConfig {

    /**
     * hope this method executed after system initialized.
     */
    @PostConstruct
    public void init(){
        System.out.println("system initialized completed...");
    }

    public void destroy(){
        System.out.println("system destroy completed...");
    }
}
