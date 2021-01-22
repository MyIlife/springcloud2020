package com.ilife.springcloud.service;

import com.ilife.springcloud.entities.CommonResult;
import com.ilife.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //注册中心的服务名
public interface PaymentOpenfeignService {
    /**
     * 这里必须加@RequestParam，否则一个405送给你
     * @param id
     * @return
     */
    @GetMapping("/payment/get")
    CommonResult<Payment> getPaymentById(@RequestParam("id") Long id);

    @GetMapping("/payment/openfeign/timeout")
    String timeout();
}
