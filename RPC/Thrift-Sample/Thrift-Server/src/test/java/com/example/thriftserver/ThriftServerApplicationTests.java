package com.example.thriftserver;

import com.example.calculator.TCalculatorService;
import com.example.calculator.TDivisionByZeroException;
import com.example.calculator.TOperation;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ThriftServerApplicationTests {

    @Autowired
    protected TProtocolFactory tProtocolFactory;

    @Value("${local.server.port}")
    protected int port;

    protected TCalculatorService.Client client;

    @BeforeEach
    public void setUp() throws Exception {
        TTransport transport = new THttpClient("http://localhost:" + port + "/calculator/");

        TProtocol protocol = tProtocolFactory.getProtocol(transport);

        client = new TCalculatorService.Client(protocol);
    }

    @Test
    public void add() throws Exception {
        assertEquals(5, client.calculate(2, 3, TOperation.ADD));
    }

    @Test
    public void subtract() throws Exception {
        assertEquals(3, client.calculate(5, 2, TOperation.SUBTRACT));
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(10, client.calculate(5, 2, TOperation.MULTIPLY));
    }

    @Test
    public void divide() throws Exception {
        assertEquals(2, client.calculate(10, 5, TOperation.DIVIDE));
    }

    @Test
    public void divisionByZero() throws Exception {
        Assertions.assertThrows(TDivisionByZeroException.class, () -> {
            client.calculate(10, 0, TOperation.DIVIDE);
        });
    }
}
