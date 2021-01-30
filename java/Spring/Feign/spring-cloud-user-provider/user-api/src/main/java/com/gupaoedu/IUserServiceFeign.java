package com.gupaoedu;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("spring-cloud-user-provider")
public interface IUserServiceFeign extends IUserService{
}
