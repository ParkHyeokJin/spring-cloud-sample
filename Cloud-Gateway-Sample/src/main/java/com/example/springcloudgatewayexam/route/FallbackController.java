package com.example.springcloudgatewayexam.route;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/circuitbreakerfallback")
    public String circuitbreakerfallback() {
        return "This is a fallback";
    }
}
