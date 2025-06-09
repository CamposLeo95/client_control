package com.client_control.client_control.dtos;

public record ClientDTO(
        String name,
        String login,
        String password,
        String phone,
        String email
) {
}
