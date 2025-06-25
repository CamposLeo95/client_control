package com.client_control.client_control.services;

import com.client_control.client_control.dtos.user.UserRequestDTO;
import com.client_control.client_control.dtos.user.UserResponseDTO;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.UserMapper;
import com.client_control.client_control.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequestDTO dto) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        var newUserRequestDTO =  new UserRequestDTO(
                dto.name(),
                dto.email(),
                dto.login(),
                encryptedPassword,
                dto.role()
        );

        userRepository.save(UserMapper.toEntity(newUserRequestDTO));
    }

    public UserResponseDTO findUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário nao encontrado!")
        );
        return UserMapper.toResponseDTO(user);
    }

    public User findUserByLogin(String login){
        return userRepository.findByLogin(login).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado!")
        );
    }

}
