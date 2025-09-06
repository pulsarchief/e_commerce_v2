package com.priyanshu.e_commerce_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ECommerceV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceV2Application.class, args);
    }

}
