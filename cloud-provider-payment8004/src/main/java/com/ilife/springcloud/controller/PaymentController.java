package com.ilife.springcloud.controller;

import com.ilife.springcloud.entities.CommonResult;
import com.ilife.springcloud.entities.Payment;
import com.ilife.springcloud.service.PaymentService;
import com.sun.org.apache.xpath.internal.objects.XNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@Data
public class PaymentController {
    @Resource
    private PaymentService paymentService;


    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/zk")
    public String paymentzk() {
        return "springcloud with zookeeper:" + port + "\t" + UUID.randomUUID().toString();
    }
}
