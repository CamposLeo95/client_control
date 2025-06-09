package com.client_control.client_control.services;

import com.client_control.client_control.dtos.ClientRequestDTO;
import com.client_control.client_control.dtos.ClientResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.mappers.ClientMapper;
import com.client_control.client_control.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void createClient(ClientRequestDTO dto) {
        clientRepository.save(ClientMapper.toEntity(dto));
    }

    public List<ClientResponseDTO> getAllClient(){
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toResponseDTO)
                .toList();
    }
}
