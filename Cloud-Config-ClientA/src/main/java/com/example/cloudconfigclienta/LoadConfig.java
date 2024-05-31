package com.example.cloudconfigclienta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class LoadConfig {
    @Value("${bar}")
    String bar;

    @Value("${foo}")
    String foo;

    public String getBar() {
        return bar;
    }

    public String getFoo() {
        return foo;
    }

    @Override
    public String toString() {
        return "LoadConfig{" +
                "bar='" + bar + '\'' +
                ", foo='" + foo + '\'' +
                '}';
    }
}
