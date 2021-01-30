package com.gupaoedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * Use SimpleMappingExceptionResolver and pass exception message to ModelAndView
	 * @return
	 */
//	@Bean
//	public SimpleMappingExceptionResolver getMySimpleMappingExceptionResolver(){
//		SimpleMappingExceptionResolver mapping = new SimpleMappingExceptionResolver(){
//			@Override
//			protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//				ModelAndView view = super.doResolveException(request, response, handler, ex);
//				view.addObject("error",ex.toString());
//				return view;
//			}
//		};
//		Properties mappings = new Properties();
//		mappings.setProperty("java.lang.NullPointerException","error1");
//		mappings.setProperty("java.lang.ArithmeticException","error2");
//
//		mapping.setExceptionMappings(mappings);
//		return mapping;
//	}

}
