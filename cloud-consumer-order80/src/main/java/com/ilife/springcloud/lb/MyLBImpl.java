package com.ilife.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLBImpl implements MyLB {
    private AtomicInteger times = new AtomicInteger(0);
    @Override
    public ServiceInstance getInstance(List<ServiceInstance> allInstances) {
        return allInstances.get(getIndex(allInstances.size()));
    }
    private final int getIndex(int nums){
        int current;
        int next;
        do{
            current = times.get();
            next =  (current >= Integer.MAX_VALUE)?0:(current+1);
        }while (!times.compareAndSet(current,next));
        return next%nums;
    }
}
