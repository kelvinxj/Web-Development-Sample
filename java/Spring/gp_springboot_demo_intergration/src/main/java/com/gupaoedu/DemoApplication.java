package com.gupaoedu;

import com.gupaoedu.filter.SecondFilter;
import com.gupaoedu.listener.Secondistener;
import com.gupaoedu.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//when springboot started, will scan @WebServlet annotation
@ServletComponentScan
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean getRegistrarBean(){
        //Include bean to ServletRegistrationBean;
        ServletRegistrationBean bean = new ServletRegistrationBean(new SecondServlet());
        bean.addUrlMappings("/second");
        return bean;
    }

    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new SecondFilter());
        bean.addUrlPatterns("/second");
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean getListener(){
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean(new Secondistener());
        return bean;
    }

}
