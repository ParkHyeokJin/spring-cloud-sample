package com.example.grpcserver;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GRpcServerController extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    private final CalculatorService calculatorService;

    public GRpcServerController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Override
    public void calculator(com.example.grpcserver.Request request, StreamObserver<com.example.grpcserver.Response> responseObserver) {
        int result = calculate(request.getA(), request.getB(), request.getOp());
        responseObserver.onNext(Response.newBuilder().setNum(result).build());
        responseObserver.onCompleted();
    }

    public int calculate(int num1, int num2, Request.TOperation op) throws IllegalArgumentException {
        switch(op) {
            case ADD:
                return calculatorService.add(num1, num2);
            case SUBTRACT:
                return calculatorService.subtract(num1, num2);
            case MULTIPLY:
                return calculatorService.multiply(num1, num2);
            case DIVIDE:
                return calculatorService.divide(num1, num2);
            default:
                throw new IllegalArgumentException("Unknown operation " + op);
        }
    }
}
