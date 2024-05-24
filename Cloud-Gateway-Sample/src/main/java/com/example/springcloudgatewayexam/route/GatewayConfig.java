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
                        .filters(f -> f.rewritePath("/default/(?<segment>.*)", "/${segment}"))
                        .uri("lb://SIMPLECLIENTA"))
                .route("circuitbreaker_route", r -> r.path("/circuit/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("myCircuitBreaker").setFallbackUri("forward:/circuitbreakerfallback").addStatusCode("INTERNAL_SERVER_ERROR"))
                                .rewritePath("/circuit/(?<segment>.*)", "/${segment}"))
                        .uri("lb://SIMPLECLIENTA"))
                .route("retry_route", r -> r.path("/retry/**")
                        .filters(f -> f.retry(3).rewritePath("/default/(?<segment>.*)", "/${segment}"))
                                .uri("lb://SIMPLECLIENTA")
                )
                .build();
    }

    public RouteDefinitionRepository getRouteDefinitionRepository() {
        return routeDefinitionRepository;
    }
}
