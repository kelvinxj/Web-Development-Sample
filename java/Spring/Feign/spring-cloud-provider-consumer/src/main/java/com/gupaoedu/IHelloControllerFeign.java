package com.gupaoedu;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="spring-cloud-provider-service", configuration = FeignLogConfig.class)
public interface IHelloControllerFeign {

    //Feign use JDK urlconnection for remote connection.
    //this url should be same as url in service provider.
    @GetMapping("/hello")
    public String sayHello();
}
