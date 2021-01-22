package com.ilife.springcloud.controller;

import com.ilife.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@DefaultProperties(defaultFallback = "globelFallBack") //全局服务降级控制,只需要在这个类中对应的方法下加入注解@HystrixCommand 即可表示这个方法做服务降级
//如果需要自定义降级策略，可以使用下面注释掉的完整的@HystrixCommand
public class OrderFeignHystrixController {
    @Autowired
    PaymentFeignHystrixService paymentFeignHystrixService;
    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return paymentFeignHystrixService.paymentInfo(id);
    }
/*    @HystrixCommand(fallbackMethod = "timeoutFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000") //x秒内就是正常
    })*/
    //@HystrixCommand
    @GetMapping("/consumer/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Long id){
/*        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //int i = 10/0;
        return paymentFeignHystrixService.timeout(id);
    }
    public String timeoutFallBack(Long id){
        return "线程池："+Thread.currentThread().getName()+" ->id = "+id+"==80调用其他服务响应超时或异常 timeoutFallBack== 系统繁忙o(╥﹏╥)o";
    }
    public String globelFallBack(){
        return "线程池："+Thread.currentThread().getName()+"   80全局异常o(╥﹏╥)o";
    }
}
