package com.example.springcloudeurekaexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaExamApplication.class, args);
    }

}
