package com.ilife.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentConsulController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/consul")
    public String paymentzk() {
        return "springcloud with consul:" + port + "\t" + UUID.randomUUID().toString();
    }
}

