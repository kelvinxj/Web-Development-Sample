package com.gupaoedu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
/**
 * predicateFactory class name should be:
 * XXXXPredicateFactory!
 */
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

    Logger logger = LoggerFactory.getLogger(AuthRoutePredicateFactory.class);

    public AuthRoutePredicateFactory() {
        super(Config.class);
    }

    public static final String NAME_KEY = "name";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        logger.info("AuthRoutePredicateFactory.apply");
        return exchange ->{
            HttpHeaders header = exchange.getRequest().getHeaders();
            List<String> headerList = header.get(config.getName()); //from configuration file
            if(headerList == null || headerList.size() <= 0)
                return false;
            else
                return true;
        };
    }

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
