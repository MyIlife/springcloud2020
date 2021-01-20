package com.ilife.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    /**
     * restful接口请求标准spring模板
     * @return
     */
    @Bean
    //@LoadBalanced //负载均衡【轮询】
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
