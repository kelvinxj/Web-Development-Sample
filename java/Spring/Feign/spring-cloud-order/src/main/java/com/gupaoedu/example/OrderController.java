package com.gupaoedu.example;

import com.gupaoapi.IUserServiceFeign;
import com.gupaoapi.User;
import com.gupaoedu.goods.clients.IGoodsQueryServiceFeignClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    IUserServiceFeign iUserServiceFeign;

    @Autowired
    IGoodsQueryServiceFeignClient iGoodsQueryServiceFeignClient;

    @Autowired
    RestTemplate  restTemplate;

    @Bean
    private RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @PostMapping("/order")
    public String doOrder(){
        //User user  = iUserServiceFeign.query();
        //String result1 = iGoodsQueryServiceFeignClient.queryGoods();

        User user = (User)restTemplate.getForObject("http://localhost:8088/query", User.class);
        String result1 = (String)restTemplate.getForObject("http://localhost:8086/goods",String.class);

        return user.toString() + result1;
    }
}
