package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public class ClientMapper {

    public static ClientResponseDTO toResponseDTO(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getLogin(),
                client.getPhone(),
                client.getPassword(),
                client.getEmail(),
                client.getCreatedAt()
        );
    }

    public static Client toEntity(ClientRequestDTO dto, User user ){
        Client client = new Client(
                dto.name(),
                dto.login(),
                dto.password(),
                dto.phone(),
                dto.email()
        );

        client.setUser(user);

        return client;
    }
}
