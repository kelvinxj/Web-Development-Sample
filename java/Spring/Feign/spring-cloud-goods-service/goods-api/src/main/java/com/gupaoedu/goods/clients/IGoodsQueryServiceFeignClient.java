package com.gupaoedu.goods.clients;

import com.gupaoedu.goods.IGoodsQueryService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "spring-cloud-goods-service")
public interface IGoodsQueryServiceFeignClient extends IGoodsQueryService {
}
