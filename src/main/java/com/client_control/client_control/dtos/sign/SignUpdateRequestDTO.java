package com.client_control.client_control.dtos.sign;

import java.time.LocalDate;

public record SignUpdateRequestDTO(
        LocalDate expireDate
) {
}
