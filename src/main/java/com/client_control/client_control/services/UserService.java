package com.client_control.client_control.services;

import com.client_control.client_control.dtos.user.UpdatePasswordRequestDTO;
import com.client_control.client_control.dtos.user.UserRequestDTO;
import com.client_control.client_control.dtos.user.UserResponseDTO;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.entities.UserDetailsImpl;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.UserMapper;
import com.client_control.client_control.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRequestDTO dto) {

        String encryptedPassword = passwordEncoder.encode(dto.password());

        var newUserRequestDTO =  new UserRequestDTO(
                dto.name(),
                dto.email(),
                dto.login(),
                encryptedPassword,
                dto.role()
        );

        userRepository.save(UserMapper.toEntity(newUserRequestDTO));
    }


    public void updateUserPassword(UUID id, UpdatePasswordRequestDTO dto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("usuário não encontrado")
        );

        String encryptedPassword = passwordEncoder.encode(dto.password());

        user.setPassword(encryptedPassword);

        userRepository.save(user);

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
    public  User mySelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
       return findUserByLogin(userDetails.getUsername());
    }


}
