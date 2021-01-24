package com.ilife.springcloud.controller;

import com.ilife.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider iMessageProvider;
    @GetMapping("/send")
    public String sendMessage(){
        return iMessageProvider.send();
    }
}
