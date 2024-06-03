package com.example.thriftclient;

import com.example.calculator.TCalculatorService;
import com.example.calculator.TOperation;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Service;

@Service
public class ThriftClientService {

    public int calculate(int a, int b, TOperation op){
        try(TTransport transport = new THttpClient("http://localhost:8080/calculator")){

            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);

            TCalculatorService.Client client = new TCalculatorService.Client(protocol);

            return client.calculate(a, b, op);
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }

    public int socketCalculate(int a, int b, TOperation op){
        try{
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            // 프로토콜 설정 (BinaryProtocol 사용)
            TProtocol protocol = new TBinaryProtocol(transport);

            // 서비스 클라이언트 생성
            TCalculatorService.Client client = new TCalculatorService.Client(protocol);

            // 서버로 요청을 보내고 응답 받기
            int result = client.calculate(a, b, op);

            // 서버 응답 출력
            System.out.println("Response from server: " + result);

            transport.close();

            return result;
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }
}
