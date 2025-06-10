package com.client_control.client_control.dtos.client;

public record ClientResponseDTO(
        Long id,
        String name,
        String login,
        String phone,
        String password,
        String email
) { }
