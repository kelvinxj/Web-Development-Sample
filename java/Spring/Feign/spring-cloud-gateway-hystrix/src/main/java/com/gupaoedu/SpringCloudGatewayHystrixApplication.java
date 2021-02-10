package com.gupaoedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class SpringCloudGatewayHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayHystrixApplication.class, args);
	}

}
