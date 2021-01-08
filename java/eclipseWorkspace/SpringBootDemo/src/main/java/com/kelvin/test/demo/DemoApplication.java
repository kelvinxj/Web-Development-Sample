package com.kelvin.test.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args ->{
			System.out.println("lets' inspect beans provided by Spring Boot:");
			
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for(String beanName: beanNames) {
				System.out.println(beanName);
			}
		};
		//this is the same as:
		
//		return new CommandLineRunner() {
//
//			@Override
//			public void run(String... args) throws Exception {
//				System.out.println("lets' inspect beans provided by Spring Boot:");
//				String[] beanNames = ctx.getBeanDefinitionNames();
//				Arrays.sort(beanNames);
//				for(String beanName: beanNames) {
//					System.out.println(beanName);
//				}
//			}
//			
//		};
	}
}
