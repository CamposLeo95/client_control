package com.client_control.client_control.dtos.payment;

import com.client_control.client_control.dtos.client.ClientDTO;
import com.client_control.client_control.dtos.sign.SignDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponseDTO(
        UUID id,
        BigDecimal value,
        String description,
        ClientDTO.RecordClientDTO client,
        SignDTO.RecordSignDTO sign
) {}
