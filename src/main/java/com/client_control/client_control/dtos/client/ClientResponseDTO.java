package com.client_control.client_control.dtos.client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientResponseDTO(
        Long id,
        String name,
        String login,
        String phone,
        String password,
        String email,
        LocalDateTime createdAt
) { }
