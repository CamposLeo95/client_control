package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.client.ClientDTO;
import com.client_control.client_control.dtos.payment.PaymentDTO;
import com.client_control.client_control.dtos.payment.PaymentResponseDTO;
import com.client_control.client_control.dtos.sign.SignDTO;
import com.client_control.client_control.entities.Payment;

public class PaymentMapper {

    public static PaymentResponseDTO toResponseDTO(Payment payment){
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getValue(),
                payment.getDescription(),
                ClientDTO.toClientDTO(payment.getClient()),
                SignDTO.toSignDTO(payment.getSign())
        );
    }
}
