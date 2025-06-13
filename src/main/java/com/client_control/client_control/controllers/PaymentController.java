package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.payment.PaymentRequestDTO;
import com.client_control.client_control.entities.Payment;
import com.client_control.client_control.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Void> createPayment(@RequestBody PaymentRequestDTO dto) {
        paymentService.createPayment(dto);

        return ResponseEntity.ok().build();
    }
}
