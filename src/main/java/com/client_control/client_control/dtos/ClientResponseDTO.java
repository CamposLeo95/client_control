package com.client_control.client_control.dtos;

public record ClientResponseDTO(
        Long id,
        String name,
        String login,
        String phone,
        String email
) { }
