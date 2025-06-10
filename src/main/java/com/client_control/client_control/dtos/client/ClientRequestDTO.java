package com.client_control.client_control.dtos.client;

import java.util.UUID;

public record ClientRequestDTO(
        String name,
        String login,
        String password,
        String phone,
        String email,
        UUID user_id
) {
}
