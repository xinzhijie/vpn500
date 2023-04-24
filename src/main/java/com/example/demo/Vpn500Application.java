package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Vpn500Application {

    public static void main(String[] args) {
        SpringApplication.run(Vpn500Application.class, args);
    }

}
