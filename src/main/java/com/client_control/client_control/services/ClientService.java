package com.client_control.client_control.services;

import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.ClientMapper;
import com.client_control.client_control.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void createClient(ClientRequestDTO dto) {
        clientRepository.save(ClientMapper.toEntity(dto));
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }

    public List<ClientResponseDTO> getAllClient(){
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toResponseDTO)
                .toList();
    }
}
