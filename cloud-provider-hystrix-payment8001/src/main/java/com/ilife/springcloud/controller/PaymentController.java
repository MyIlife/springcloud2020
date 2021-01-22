package com.ilife.springcloud.controller;

import com.ilife.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        String s = paymentService.paymentInfo(id);
        log.info("port:"+port+" 结果："+ s);
        return s;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Long id){
        String s = paymentService.timeout(id);
        log.info("port:"+port+" 结果："+ s);
        return s;
    }
    //============服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String re = paymentService.paymentCircuitBreaker(id);
        log.info("*******result:"+re);
        return re;

    }
}
