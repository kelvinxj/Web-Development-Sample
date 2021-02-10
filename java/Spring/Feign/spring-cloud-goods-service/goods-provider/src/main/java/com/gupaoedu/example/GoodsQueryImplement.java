package com.gupaoedu.example;

import com.gupaoedu.goods.IGoodsQueryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsQueryImplement implements IGoodsQueryService {
    @Override
    public String queryGoods() {
        return "goods info";
    }
}
