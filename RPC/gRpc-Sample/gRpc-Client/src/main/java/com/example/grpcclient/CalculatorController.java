package com.example.grpcclient;

import com.example.grpcserver.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public int add(@RequestParam("a") int a, @RequestParam("b") int b){
        return calculatorService.calculator(a, b, Request.TOperation.ADD);
    }

    @GetMapping("/subtract")
    public int subtract(@RequestParam("a") int a, @RequestParam("b") int b){
        return calculatorService.calculator(a, b, Request.TOperation.SUBTRACT);
    }

    @GetMapping("/multiply")
    public int multiply(@RequestParam("a") int a, @RequestParam("b") int b){
        return calculatorService.calculator(a, b, Request.TOperation.MULTIPLY);
    }

    @GetMapping("/divide")
    public int divide(@RequestParam("a") int a, @RequestParam("b") int b){
        return calculatorService.calculator(a, b, Request.TOperation.DIVIDE);
    }
}
