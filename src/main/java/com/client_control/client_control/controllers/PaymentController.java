package com.client_control.client_control.controllers;

import com.client_control.client_control.entities.Payment;
import com.client_control.client_control.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public ResponseEntity<Void> createPayment(Payment payment) {
        paymentService.createPayment(payment);

        return ResponseEntity.ok().build();
    }
}
