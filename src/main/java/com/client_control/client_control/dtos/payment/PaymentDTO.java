package com.client_control.client_control.dtos.payment;

import com.client_control.client_control.entities.Payment;

public class PaymentDTO {

    public record RecordPaymentDTO (){}

    public static RecordPaymentDTO toPaymentDTO(Payment payment){
        return new RecordPaymentDTO();
    }
}
