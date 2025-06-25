package com.client_control.client_control.dtos.serviceOffering;

import java.math.BigDecimal;

public record ServiceOfferingResponseDTO(Long id, String name, BigDecimal price) {
}
