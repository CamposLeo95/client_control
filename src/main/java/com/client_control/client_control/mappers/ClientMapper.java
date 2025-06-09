package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.ClientRequestDTO;
import com.client_control.client_control.dtos.ClientResponseDTO;
import com.client_control.client_control.entities.Client;

public class ClientMapper {

    public static ClientResponseDTO toResponseDTO(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getLogin(),
                client.getPhone(),
                client.getEmail()
        );
    }

    public static Client toEntity(ClientRequestDTO dto){
        return new Client(
                dto.name(),
                dto.login(),
                dto.password(),
                dto.phone(),
                dto.email()
        );
    }
}
