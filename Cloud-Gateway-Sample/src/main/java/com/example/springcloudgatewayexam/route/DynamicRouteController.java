package com.example.springcloudgatewayexam.route;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/routes")
public class DynamicRouteController {

    private final GatewayConfig gatewayConfig;

    public DynamicRouteController(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
    }

    @PostMapping
    public Mono<String> addRoute(@RequestBody RouteDefinition routeDefinition) {
        routeDefinition.setId(routeDefinition.getId());
        return gatewayConfig.getRouteDefinitionRepository().save(Mono.just(routeDefinition)).then(Mono.just("Route added successfully"));
    }

    @DeleteMapping("/{routeId}")
    public Mono<String> deleteRoute(@PathVariable String routeId) {
        return gatewayConfig.getRouteDefinitionRepository().delete(Mono.just(routeId)).then(Mono.just("Route deleted successfully"));
    }

    @GetMapping
    public Mono<Map<String, RouteDefinition>> getRoutes() {
        Map<String, RouteDefinition> routes = new HashMap<>();
        gatewayConfig.getRouteDefinitionRepository().getRouteDefinitions()
                .collectList()
                .map(routeDefinitions -> {
                    routeDefinitions.forEach(routeDefinition -> routes.put(routeDefinition.getId(), routeDefinition));
                    return routes;
                });
        return Mono.just(routes);
    }
}
