package com.ilife.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLB {
    ServiceInstance getInstance(List<ServiceInstance> allInstances);
}
