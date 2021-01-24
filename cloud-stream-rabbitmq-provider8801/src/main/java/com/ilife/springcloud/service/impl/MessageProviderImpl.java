package com.ilife.springcloud.service.impl;

import com.ilife.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output; //这里必须要指定beanname为output,不然依赖注入会报错
/*    Field messageChannel in com.ilife.springcloud.service.impl.MessageProviderImpl required a single bean, but 3 were found:
            - output: defined by method 'output' in null
            - nullChannel: defined in null
            - errorChannel: defined in null*/
    @Override
    public String send() {
        String s = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(s).build());
        System.out.println("***********s:"+s);
        return s;
    }
}
