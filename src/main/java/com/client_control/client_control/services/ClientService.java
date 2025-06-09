package com.client_control.client_control.services;

import com.client_control.client_control.dtos.ClientDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void createClient(ClientDTO dto) {
        Client client = new Client(
                dto.name(),
                dto.login(),
                dto.password(),
                dto.phone(),
                dto.email()
        );
        this.clientRepository.save(client);
    }
}
