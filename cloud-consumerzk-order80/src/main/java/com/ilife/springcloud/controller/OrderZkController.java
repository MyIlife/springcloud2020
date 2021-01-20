package com.ilife.springcloud.controller;

import com.ilife.springcloud.entities.CommonResult;
import com.ilife.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZkController {

    //public static final String PAYMENT_URL = "http://localhost:8001";//单机模式
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumerZk/paymentInfo")
    public String paymentInfo(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zk",String.class);
    }
}
