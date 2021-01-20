package com.ilife.springcloud.com.ilife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderConsulController {
    public static final String PAYMENT_URL = "http://cloud-paymentconsul-service";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consul/paymentInfo")
    public String paymentInfo(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }
}
