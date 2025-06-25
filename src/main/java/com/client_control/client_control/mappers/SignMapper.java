package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.client.ClientDTO;
import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingDTO;
import com.client_control.client_control.dtos.sign.SignResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.Sign;

public class SignMapper {

    public static SignResponseDTO toResponseDTO(Sign sign){

        return new SignResponseDTO(
                sign.getId(),
                sign.isActiveSign(),
                sign.getExpireDate(),
                ClientDTO.toClientDTO(sign.getClient()),
                ServiceOfferingDTO.toServiceOfferingDTO(sign.getServiceOffering())
        );
    }

}


