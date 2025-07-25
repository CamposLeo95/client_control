package com.client_control.client_control.dtos.sign;

import com.client_control.client_control.dtos.client.ClientDTO;
import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.ServiceOffering;
import com.client_control.client_control.mappers.SignMapper;

import java.time.LocalDate;

public record SignResponseDTO(
        Long id,
        boolean activeSign,
        LocalDate expireDate,
        ClientDTO.RecordClientDTO client,
        ServiceOfferingDTO.RecordServiceOfferingDTO serviceOffering,
        String description
) {}
