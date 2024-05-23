package com.example.springcloudgatewayexam.route;

import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private final RouteDefinitionRepository routeDefinitionRepository;

    //redis routeDefinitionRepository
    //private final RedisRouteDefinitionRepository redisRouteDefinitionRepository;

    public GatewayConfig(RouteDefinitionRepository routeDefinitionRepository) {
        this.routeDefinitionRepository = routeDefinitionRepository;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("default_route", r -> r.path("/default/**")
                        .uri("http://example.com"))
                .build();
    }

    public RouteDefinitionRepository getRouteDefinitionRepository() {
        return routeDefinitionRepository;
    }
}
