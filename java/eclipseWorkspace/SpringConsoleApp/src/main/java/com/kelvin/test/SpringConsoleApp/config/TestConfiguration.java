package com.kelvin.test.SpringConsoleApp.config;

import org.springframework.context.annotation.*;

import com.kelvin.test.SpringConsoleApp.beans.HelloWorld;

@Configuration
//it will scan all class in package name begin with "com.kelvin.test.SpringConsoleApp" and annotated with "@Service, @Controller"
@ComponentScan(value= "com.kelvin.test.SpringConsoleApp")
@ImportResource(value="classpath:beans.xml")
/*
 * this class equals beans.xml.
 * you can put all bean definition at this class
 */
public class TestConfiguration {
	public TestConfiguration() {
		System.out.println("Test configuration initialized...");
	}
	
	public String getName() {
		return "My name is TestConfiguration class";
	}
	
	@Bean(initMethod="start",destroyMethod="cleanUp")
	@Scope("prototype")
	public HelloWorld getHelloWorld() {
		return new HelloWorld();
	}
}
