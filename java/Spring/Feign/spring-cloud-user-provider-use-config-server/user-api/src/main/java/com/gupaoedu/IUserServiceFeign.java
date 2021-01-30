package com.gupaoedu;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "spring-cloud-user-provider", fallback = IUserServiceFeign.DefaultFallback.class)
public interface IUserServiceFeign extends IUserService{

    @Component
    class DefaultFallback implements  IUserServiceFeign{

        @Override
        public String insertUser(User user) {
            return "Service downgrade in Feign...";
        }
    }
}
