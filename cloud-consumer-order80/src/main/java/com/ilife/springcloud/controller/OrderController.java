package com.ilife.springcloud.controller;

import com.ilife.springcloud.entities.CommonResult;
import com.ilife.springcloud.entities.Payment;
import com.ilife.springcloud.lb.MyLB;
import com.ilife.springcloud.lb.MyLBImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";//单机模式
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get?id="+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getPayment1(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get?id="+id,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            CommonResult body = entity.getBody();
            return body;
        }else{
            CommonResult commonResult = new CommonResult(444,"失败");
            return commonResult;
        }
    }

    @Autowired
    DiscoveryClient discoveryClient;
    @Resource
    MyLB myLB;
    @GetMapping("/consumer/lb/test")
    public String getPort(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = myLB.getInstance(instances);
        return restTemplate.getForObject(instance.getUri()+"/payment/getPort",String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}
