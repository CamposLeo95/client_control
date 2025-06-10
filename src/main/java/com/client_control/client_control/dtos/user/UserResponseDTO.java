package com.client_control.client_control.dtos.user;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String login,
        boolean admin
) {
}
