package com.gupaoedu;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired()
    @Qualifier("LoadBalanceRestTemplate")
    private RestTemplate restTemplate;

    @Bean(value="LoadBalanceRestTemplate")
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Autowired()
    @Qualifier("NonloadBalanceRestTemplate")
    private RestTemplate nonLBRestTemplate;

    @Bean(value="NonloadBalanceRestTemplate")
    public RestTemplate getNonLBRestTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/test")
    public String test(){
        //1. call eureka discovery client to get service provider url list
        //2. get one url by algorithm
        String url = "http://spring-cloud-provider-service/hello";
        return restTemplate.getForObject(url, String.class);
    }
    @GetMapping("/test1")
    public String test1(){
        //get instance by loadbanlancer choose.
        //to use this way, remove @LoadBalanced annotation from restTemplate

        ServiceInstance service = loadBalancerClient.choose("spring-cloud-provider-service");
        String url = String.format("http://%s:%s", service.getHost(), service.getPort() + "/hello");
        return nonLBRestTemplate.getForObject(url, String.class);
    }
}
