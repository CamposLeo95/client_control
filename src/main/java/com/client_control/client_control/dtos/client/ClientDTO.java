package com.client_control.client_control.dtos.client;

import com.client_control.client_control.entities.Client;


public class ClientDTO {

    public record RecordClientDTO (
            Long id,
            String name,
            String email,
            String login,
            String password,
            String phone
    ){}

    public static RecordClientDTO toClientDTO(Client client) {
        if (client == null) return null;
        return new RecordClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getLogin(),
                client.getPassword(),
                client.getPhone()
        );
    }
}