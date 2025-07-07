package com.client_control.client_control.dtos.user;

public record SendEmailDTO(
        String setFrom,
        String setTo,
        String setSubject,
        String setText
) {}
