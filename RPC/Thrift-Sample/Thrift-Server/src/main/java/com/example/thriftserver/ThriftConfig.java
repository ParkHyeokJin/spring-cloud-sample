package com.example.thriftserver;

import com.example.calculator.TCalculatorService;
import jakarta.servlet.http.HttpServlet;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServlet;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThriftConfig {

    private final CalculatorServiceHandler handler;

    public ThriftConfig(CalculatorServiceHandler handler) {
        this.handler = handler;
    }

    @Bean
    public TProtocolFactory tProtocolFactory() {
        //We will use binary protocol, but it's possible to use JSON and few others as well
        return new TBinaryProtocol.Factory();
    }

    @Bean
    public ServletRegistrationBean<HttpServlet> stateServlet(TProtocolFactory tProtocolFactory, CalculatorServiceHandler handler) {
        ServletRegistrationBean <HttpServlet> servRegBean = new ServletRegistrationBean<>();
        servRegBean.setServlet(new TServlet(new TCalculatorService.Processor <>(handler), tProtocolFactory));
        servRegBean.addUrlMappings("/calculator/*");
        servRegBean.setLoadOnStartup(1);
        return servRegBean;
    }

//    @Bean
//    public TServer thriftServer(TProtocolFactory tProtocolFactory) throws TTransportException {
//        // TCP 서버 소켓 설정
//        TServerSocket serverTransport = new TServerSocket(9090);
//
//        // Thrift 서비스를 서버에 등록
//        TSimpleServer.Args args = new TSimpleServer.Args(serverTransport);
//        args.protocolFactory(tProtocolFactory);
//        args.processor(new TCalculatorService.Processor<>(handler));
//
//        System.out.println("start Socket server. port 9090.");
//
//        // Thrift 서버 생성 및 시작
//        return new TSimpleServer(args);
//    }
}