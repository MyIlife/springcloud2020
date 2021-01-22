package com.ilife.springcloud.controller;

import com.ilife.springcloud.entities.CommonResult;
import com.ilife.springcloud.entities.Payment;
import com.ilife.springcloud.service.PaymentOpenfeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderOpenfeignController {
    @Autowired
    private PaymentOpenfeignService paymentOpenfeignService;
    @GetMapping("/consumer/feign/getPayment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> payment = paymentOpenfeignService.getPaymentById(id);
        return payment;
    }

    @GetMapping("/consumer/openfeign/timeout")
    public String timeout(){
        //openfeign-ribbon服务调用默认等待1秒，超时将报错
        return paymentOpenfeignService.timeout();
    }
}
