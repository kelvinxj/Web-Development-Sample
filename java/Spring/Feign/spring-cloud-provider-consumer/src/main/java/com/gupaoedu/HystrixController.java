package com.gupaoedu;

import com.gupaoapi.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @Autowired()
    @Qualifier("hystrix")
    private RestTemplate nonLBRestTemplate;

    @Bean(name="hystrix")
    public RestTemplate hystrixRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/hystrix/user")
    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String queryUser(){
        User user = new User();
        user.setName("xiejia");
        return nonLBRestTemplate.postForObject("http://localhost:8088/user",user, String.class);
    }

    public String fallback(){
        return "System is busy, 请稍后再试...";
    }


    /**
     * means if in 10 seconds, 50% of 5 requests failed, circuit breaker will be triggered.
     * and after 30 seconds, one request is allowed to retry.
     * @return
     */
    @GetMapping("/hystrix/circuitbreaker/{num}")
    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "500"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "30000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50")
    })
    public String queryUserCircuitBreaker(@PathVariable int num){
        if(num % 2 == 0)
            return "success request.";
        else {
            User user = new User();
            user.setName("xiejia");
            return nonLBRestTemplate.postForObject("http://localhost:8088/user",user, String.class);
        }
    }

    public String fallback(int num){
        return "System is busy, 请稍后再试...";
    }



    @GetMapping("/hystrix/user1")
    @HystrixCommand
    public String queryUser1(){
        User user = new User();
        user.setName("xiejia");
        //if wait for request more than 1 second, think the service is broken, trigger service break.
        return nonLBRestTemplate.postForObject("http://localhost:8088/user",user, String.class);
    }
    public String defaultFallback(){
        return "Default fallback.稍后再试...";
    }
}
