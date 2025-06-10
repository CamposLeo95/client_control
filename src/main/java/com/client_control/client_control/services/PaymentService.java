package com.client_control.client_control.services;

import com.client_control.client_control.entities.Payment;
import com.client_control.client_control.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public void createPayment(Payment payment){
        paymentRepository.save(payment);
    }
}
