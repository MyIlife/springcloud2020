package com.ilife.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        // 测试线程流控
/*        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "-------testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "-------testB";
    }
    @GetMapping("/testC")
    public String testC(){
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============testC");
        return "-------testC";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "fallback")
    public String testHotKey(@RequestParam(value = "id",required = false) Long id,
                          @RequestParam(value = "id1",required = false) Long id1){
        return "-----testHotKey";
    }
    public String fallback(Long id, Long id1, BlockException blockException){
        return "sentinel hotkey fallback o(╥﹏╥)o";
    }
}
