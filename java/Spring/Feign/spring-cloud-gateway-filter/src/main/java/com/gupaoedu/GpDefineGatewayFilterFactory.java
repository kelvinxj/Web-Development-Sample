package com.gupaoedu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class GpDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<GpDefineGatewayFilterFactory.Config> {

    Logger logger = LoggerFactory.getLogger(GpDefineGatewayFilterFactory.class);

    public GpDefineGatewayFilterFactory() {
        super(Config.class);
    }

    public static final String NAME_KEY="name";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //logic of filter

        return ((exchange, chain) -> {
            logger.info("[Pred] Filter Request. Name:" + config.getName());
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                String code = exchange.getResponse().getStatusCode().toString();

                logger.info("[Post] Response Filter. status:" + code);
            }));
        });
    }

//    @Override
//    public GatewayFilter apply(Object config) {
//        return null;
//    }

    public static class Config{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
