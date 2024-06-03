package com.example.grpcclient;

import com.example.grpcserver.CalculatorServiceGrpc;
import com.example.grpcserver.Request;
import com.example.grpcserver.Response;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public int calculator(int a, int b, Request.TOperation op){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        Response response = stub.calculator(Request.newBuilder().setA(a).setB(b).setOp(op).build());

        System.out.println(response.getNum());

        return response.getNum();
    }
}
