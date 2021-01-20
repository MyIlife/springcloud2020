package com.ilife.springcloud.service;

import com.ilife.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
