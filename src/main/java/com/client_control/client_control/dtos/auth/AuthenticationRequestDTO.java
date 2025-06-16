package com.client_control.client_control.dtos.auth;

public record AuthenticationRequestDTO(
        String login,
        String password
) { }
