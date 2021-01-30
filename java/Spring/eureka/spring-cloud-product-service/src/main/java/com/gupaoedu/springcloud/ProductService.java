package com.gupaoedu.springcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductService {

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable int id){
        return "SUCCESS";
    }
}
