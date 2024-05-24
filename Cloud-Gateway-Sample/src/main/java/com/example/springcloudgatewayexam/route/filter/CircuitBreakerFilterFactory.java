package com.example.springcloudgatewayexam.route.filter;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerFilterFactory {
    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(10)
                            .minimumNumberOfCalls(2)
                            .failureRateThreshold(60)
                            .waitDurationInOpenState(Duration.ofMillis(10000))
                            .build();
        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(config)
                .build(), "defaultCB");
    }
}
