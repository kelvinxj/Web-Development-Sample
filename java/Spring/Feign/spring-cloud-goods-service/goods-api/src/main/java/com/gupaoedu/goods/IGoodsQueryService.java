package com.gupaoedu.goods;

import org.springframework.web.bind.annotation.GetMapping;

public interface IGoodsQueryService {

    @GetMapping("/goods")
    public String queryGoods();
}
