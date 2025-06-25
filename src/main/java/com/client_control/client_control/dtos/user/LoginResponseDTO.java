package com.client_control.client_control.dtos.user;

import java.util.UUID;

public record LoginResponseDTO(String token, UUID user_id) {
}
