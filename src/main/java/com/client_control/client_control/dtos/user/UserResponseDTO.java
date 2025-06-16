package com.client_control.client_control.dtos.user;

import com.client_control.client_control.entities.UserRole;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String login,
        UserRole role
) {
}
