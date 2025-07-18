package com.client_control.client_control.dtos.user;

public record SendEmailDTO(
        String from,
        String to,
        String subject,
        String text
) {}
