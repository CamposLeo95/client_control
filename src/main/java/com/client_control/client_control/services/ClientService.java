package com.client_control.client_control.services;

import com.client_control.client_control.dtos.client.ClientDTO;
import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.entities.UserRole;
import com.client_control.client_control.exceptions.BusinessException;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.ClientMapper;
import com.client_control.client_control.repositories.ClientRepository;
import com.client_control.client_control.utils.SpecificationUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;

    public ClientService(ClientRepository clientRepository, UserService userService){
        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    public void createClient(ClientRequestDTO dto) {
        User user = userService.mySelf();

        if(clientRepository.findByLoginAndUserId(dto.login(), user.getId()).isPresent()){
            throw new BusinessException("Login já cadastrado!");
        }

        if(clientRepository.findByEmailAndUserId(dto.email(), user.getId()).isPresent()){
            throw new BusinessException("Email já cadastrado!");
        }


        clientRepository.save(ClientMapper.toEntity(dto, user));
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }

    public List<ClientResponseDTO> findAllClient(Specification<Client> specificationDto, Pageable pageable){
        User user = userService.mySelf();
        Specification<Client> specification = SpecificationUtils.SpecificationRole(specificationDto, user);

        return clientRepository.findAll(specification, pageable)
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
