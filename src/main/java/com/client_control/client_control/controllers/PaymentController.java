package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.payment.PaymentRequestDTO;
import com.client_control.client_control.dtos.payment.PaymentResponseDTO;
import com.client_control.client_control.services.PaymentService;
import com.client_control.client_control.specifications.SpecificationPaymentTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> findAllPayments(
            SpecificationPaymentTemplate specificationClient,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(paymentService.findAllPayments(specificationClient, pageable));
    }

    @GetMapping("{payment_id}")
    public ResponseEntity<PaymentResponseDTO> findPaymentById(@PathVariable("payment_id") UUID id){
        return  ResponseEntity.ok(paymentService.findPaymentById(id));
    }
}
