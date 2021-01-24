package com.ilife.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@EnableBinding(Sink.class)
public class MessageListenerController {
    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    private void input(Message<String> message){
        System.out.println("消费端【"+port+"】收到消息："+message.getPayload());
    }
}
