package com.example.simpleclientb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SimpleClientBApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello SimpleClient A.";
    }


    public static void main(String[] args) {
        SpringApplication.run(SimpleClientBApplication.class, args);
    }

}
