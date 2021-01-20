package com.ilife.lbrules;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    /**
     * 随机
     * @return
     */
    @Bean
    public IRule randomRule(){
        return new RandomRule();
    }
}
