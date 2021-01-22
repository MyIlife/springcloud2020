package com.ilife.springcloud.service;

import com.ilife.springcloud.service.impl.PaymentFeignHystrixServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = PaymentFeignHystrixServiceFallback.class)
public interface PaymentFeignHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String timeout(@PathVariable("id") Long id);
}
