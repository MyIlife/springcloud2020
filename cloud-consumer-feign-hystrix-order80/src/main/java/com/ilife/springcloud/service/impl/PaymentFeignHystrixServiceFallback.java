package com.ilife.springcloud.service.impl;

import com.ilife.springcloud.service.PaymentFeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignHystrixServiceFallback implements PaymentFeignHystrixService {
    @Override
    public String paymentInfo(Long id) {
        return "paymentInfo =========fallback";
    }

    @Override
    public String timeout(Long id) {
        return "timeout ============fallback";
    }
}
