package com.example.cloudconfigclienta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CloudConfigClientAApplication {

    private final LoadConfig loadConfig;

    public CloudConfigClientAApplication(LoadConfig loadConfig) {
        this.loadConfig = loadConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigClientAApplication.class, args);
    }


    @RequestMapping("/")
    public String hello(){
        return loadConfig.toString();
    }
}
