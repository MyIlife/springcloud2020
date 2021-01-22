package com.ilife.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderOpenfeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenfeignMain80.class,args);
    }
}
