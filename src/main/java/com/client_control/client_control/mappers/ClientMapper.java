package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.User;

public class ClientMapper {

    public static ClientResponseDTO toResponseDTO(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getPhone(),
                client.getEmail()
        );
    }

    public static Client toEntity(ClientRequestDTO dto){
        Client client = new Client(
                dto.name(),
                dto.login(),
                dto.password(),
                dto.phone(),
                dto.email()
        );

        User user = new User();
        user.setId(dto.user_id());
        client.setUser(user);

        return client;
    }
}
