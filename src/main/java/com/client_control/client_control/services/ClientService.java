package com.client_control.client_control.services;

import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.exceptions.BusinessException;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.ClientMapper;
import com.client_control.client_control.repositories.ClientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;

    public ClientService(ClientRepository clientRepository, UserService userService){
        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    public void createClient(ClientRequestDTO dto, String login) {
        if(clientRepository.findByLogin(dto.login()).isPresent()){
            throw new BusinessException("Login já cadastrado!");
        }

        if(clientRepository.findByEmail(dto.email()).isPresent()){
            throw new BusinessException("Email já cadastrado!");
        }

        User user = userService.findUserByLogin(login);

        clientRepository.save(ClientMapper.toEntity(dto, user));
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }

    public List<ClientResponseDTO> getAllClient(Specification<Client> specification){
        return clientRepository.findAll(specification)
                .stream()
                .map(ClientMapper::toResponseDTO)
                .toList();
    }

    public Client findClientByEmail(String email){
        return clientRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }

    public Client finClientByLogin(String login){
        return clientRepository.findByLogin(login).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }
}
