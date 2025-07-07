package com.client_control.client_control.services;

import com.client_control.client_control.dtos.user.*;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.entities.UserDetailsImpl;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.UserMapper;
import com.client_control.client_control.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Value("${app.front-url}")
    private String frontUrl;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailservice;
    private final TokenService tokenService;


    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            EmailService emailservice
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailservice = emailservice;
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


    public void updateUserPassword(String token,  UpdatePasswordRequestDTO dto) {
        var userLogin = tokenService.validateToken(token);

        User user = userRepository.findByLogin(userLogin).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
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

    public void recoveryPassword(RecoveryPasswordRequestDTO dto) {
        try {
            User user = userRepository.findByEmail(dto.email()).orElseThrow(
                    () -> new ResourceNotFoundException("Usuario não encontrado!")
            );
            var tokenRecovery = tokenService.generateToken(user);
            String link = frontUrl + "/auth/recovery-password/" + tokenRecovery;
            SendEmailDTO send = new SendEmailDTO(
                    "leocampos.995@gmail.com",
                    dto.email(),
                    "Recuperaçao de senha ",
                    link
            );
            emailservice.sendEmail(send);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email de recuperação", e);
        }
    }

}
