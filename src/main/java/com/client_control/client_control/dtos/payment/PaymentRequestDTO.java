package com.client_control.client_control.dtos.payment;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        BigDecimal value,
        String description,
        Long client_id,
        Long sign_id,
        Long serviceOffering_id
) {
}
