package com.ilife.springcloud.controller;

import com.ilife.springcloud.entities.CommonResult;
import com.ilife.springcloud.entities.Payment;
import com.ilife.springcloud.service.PaymentService;
import com.netflix.discovery.converters.Auto;
import com.sun.org.apache.xpath.internal.objects.XNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Data
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

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
        if(payment!=null) {
            log.info("port【+" + port + "】查询结果：" + payment.toString());
        }
        if(payment!=null) {
            return new CommonResult<>(200, "port【+"+port+"】--success", payment);
        }else {
            return new CommonResult<>(444, "port【+"+port+"】--no data", payment);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (int i = 0; i < services.size(); i++) {
            String s = services.get(i);
            log.info("服务名："+s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (int i = 0; i < instances.size(); i++) {
            ServiceInstance serviceInstance = instances.get(i);
            log.info(serviceInstance.getServiceId()+"======="+serviceInstance.getHost()+"======="+serviceInstance.getPort()+"======="+serviceInstance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/getPort")
    public String getPort(){
        return port;
    }
    /**
     * 测试openfeign的超时控制
     * @return
     */
    @GetMapping("/payment/openfeign/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
        return port;
    }
}
