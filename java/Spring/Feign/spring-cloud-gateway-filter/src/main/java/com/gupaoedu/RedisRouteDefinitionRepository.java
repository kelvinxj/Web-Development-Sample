package com.gupaoedu;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

//@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private final static String gateway_route_key="gateway_dynamic_route";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<RouteDefinition>();
        redisTemplate.opsForHash().values(gateway_route_key).stream().forEach(route -> {
            routeDefinitions.add(JSON.parseObject(route.toString(),RouteDefinition.class));
        });
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            redisTemplate.opsForHash().put(gateway_route_key,routeDefinition.getId()
                    , JSON.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id->{
            if(redisTemplate.opsForHash().hasKey(gateway_route_key,id)) {
                redisTemplate.opsForHash().delete(gateway_route_key, id);
                return Mono.empty();
            }
            else
                return Mono.defer(()->Mono.error(new Exception("route id not found." + routeId)));
        });
    }
}
