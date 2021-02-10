package com.gupaoedu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultHystrixController {

    @GetMapping("/defaultFallback")
    public String defaultFallback(){
        return "Gateway trigger circuit breaker";
    }
}
