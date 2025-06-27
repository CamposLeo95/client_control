package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.payment.PaymentRequestDTO;
import com.client_control.client_control.dtos.payment.PaymentResponseDTO;
import com.client_control.client_control.services.PaymentService;
import com.client_control.client_control.specifications.SpecificationPaymentTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> findAllPayments(
            SpecificationPaymentTemplate specificationClient,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(paymentService.findAllPayments(specificationClient, pageable));
    }
}
