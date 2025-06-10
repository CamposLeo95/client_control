package com.client_control.client_control.services;

import com.client_control.client_control.dtos.user.UserRequestDTO;
import com.client_control.client_control.dtos.user.UserResponseDTO;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.mappers.UserMapper;
import com.client_control.client_control.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequestDTO dto) {
        userRepository.save(UserMapper.toEntity(dto));
    }

    public UserResponseDTO findUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário nao encontrado")
        );
        return UserMapper.toResponseDTO(user);
    }

}
