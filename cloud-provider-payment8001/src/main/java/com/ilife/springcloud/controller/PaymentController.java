package com.ilife.springcloud.controller;

import com.ilife.springcloud.entities.CommonResult;
import com.ilife.springcloud.entities.Payment;
import com.ilife.springcloud.service.PaymentService;
import com.sun.org.apache.xpath.internal.objects.XNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Data
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("port【+"+port+"】插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "port【+"+port+"】--success", result);
        } else {
            return new CommonResult(444, "port【+"+port+"】--failed", null);
        }
    }
    @GetMapping("/payment/get")
    public CommonResult<Payment> getPaymentById(Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("port【+"+port+"】查询结果：" + payment.toString());
        if(payment!=null) {
            return new CommonResult<>(200, "port【+"+port+"】--success", payment);
        }else {
            return new CommonResult<>(444, "port【+"+port+"】--no data", payment);
        }
    }
}
