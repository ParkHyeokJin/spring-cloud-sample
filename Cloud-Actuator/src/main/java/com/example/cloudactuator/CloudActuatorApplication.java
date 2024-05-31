package com.example.cloudactuator;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CloudActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudActuatorApplication.class, args);
    }

    @PostConstruct
    void test(){
        System.out.println("how?");
    }

    @EventListener
    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event){
        switch (event.getState()) {
            case ACCEPTING_TRAFFIC -> {
                // create file /tmp/healthy
                System.out.println("out /tmp/healthy");
            }
            case REFUSING_TRAFFIC -> {
                // remove file /tmp/healthy
                System.out.println("remove /tmp/healthy");
            }
        }
    }


}
