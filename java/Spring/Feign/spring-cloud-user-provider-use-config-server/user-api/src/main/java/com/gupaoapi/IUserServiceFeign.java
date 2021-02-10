package com.gupaoapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@FeignClient(value = "spring-cloud-user-provider", fallback = IUserServiceFeign.DefaultFallback.class)
public interface IUserServiceFeign extends IUserService{

    @Component
    class DefaultFallback implements  IUserServiceFeign{

        @Override
        public String insertUser(User user) {
            return "Service downgrade in Feign...";
        }

        @Override
        public User query() {
            User defaultUser = new User();
            defaultUser.setName("default user");
            return new User();
        }
    }
}
