package com.example.thriftclient;

import com.example.calculator.TOperation;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThriftClientController {

    private final ThriftClientService thriftClientService;

    public ThriftClientController(ThriftClientService thriftClientService) {
        this.thriftClientService = thriftClientService;
    }

    @GetMapping("/add")
    public int add(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.calculate(a, b, TOperation.ADD);
    }

    @GetMapping("/subtract")
    public int subtract(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.calculate(a, b, TOperation.SUBTRACT);
    }

    @GetMapping("/multiply")
    public int multiply(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.calculate(a, b, TOperation.MULTIPLY);
    }

    @GetMapping("/divide")
    public int divide(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.calculate(a, b, TOperation.DIVIDE);
    }

    @GetMapping("/s_add")
    public int s_add(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.socketCalculate(a, b, TOperation.ADD);
    }

    @GetMapping("/s_subtract")
    public int s_subtract(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.socketCalculate(a, b, TOperation.SUBTRACT);
    }

    @GetMapping("/s_multiply")
    public int s_multiply(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.socketCalculate(a, b, TOperation.MULTIPLY);
    }

    @GetMapping("/s_divide")
    public int s_divide(@RequestParam("a") int a, @RequestParam("b") int b){
        return thriftClientService.socketCalculate(a, b, TOperation.DIVIDE);
    }
}
