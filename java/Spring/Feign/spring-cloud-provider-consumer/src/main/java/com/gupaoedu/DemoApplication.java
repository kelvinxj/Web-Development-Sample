package com.gupaoedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//if you need to use Feign interface, you need specify package of IUserServiceFeign
// in user-api project, that is "com.gupaoedu"
@EnableFeignClients(basePackages = "com.gupaoedu")
@ComponentScan(basePackages = {"com.gupaoedu"})
@EnableCircuitBreaker
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
