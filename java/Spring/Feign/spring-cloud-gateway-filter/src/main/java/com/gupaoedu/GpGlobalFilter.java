package com.gupaoedu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GpGlobalFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(GpGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Pre Global filter");

        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            logger.info("Post Global Filter");
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
