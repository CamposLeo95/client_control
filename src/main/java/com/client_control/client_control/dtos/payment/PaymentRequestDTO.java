package com.client_control.client_control.dtos.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentRequestDTO(
        BigDecimal value,
        String description,
        Long client_id,
        Long sign_id,
        Long serviceOffering_id,
        LocalDate manual_date
) {
}
