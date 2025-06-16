package com.client_control.client_control.dtos.user;

import com.client_control.client_control.entities.UserRole;

public record UserRequestDTO(
        String name,
        String email,
        String login,
        String password,
        UserRole role
) {
}
