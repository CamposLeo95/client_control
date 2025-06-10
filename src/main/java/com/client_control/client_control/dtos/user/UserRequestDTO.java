package com.client_control.client_control.dtos.user;

public record UserRequestDTO(
        String name,
        String email,
        String login,
        String password,
        boolean admin
) {
}
