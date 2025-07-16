package com.client_control.client_control.services;

import com.client_control.client_control.dtos.user.UserRequestDTO;
import com.client_control.client_control.dtos.user.UserResponseDTO;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.entities.UserRole;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    EmailService emailService;

    @Mock
    TokenService tokenService;

    @Test
    @DisplayName("Should create a new user with password encrypted")
    void createUserCase1() {

        UserRequestDTO dto = new UserRequestDTO(
                "Leonardo",
                "leo@email.com",
                "leo1234",
                "1234",
                UserRole.USER
        );

        when(passwordEncoder.encode(dto.password())).thenReturn("encoded-password");

        userService.createUser(dto);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(captor.capture());
        verify(passwordEncoder).encode("1234");

        User savedUser = captor.getValue();

        assertEquals("encoded-password", savedUser.getPassword());

    }

    @Test
    @DisplayName("Should return ResponseDTO when user is found by Id")
    void findUserByIdCase1() {
        var nameUser = "Leo";
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setName(nameUser);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserResponseDTO result = userService.findUserById(id);

        assertEquals(nameUser, result.name());

    }

    @Test
    @DisplayName("Should return ResourceNotFoundException when return is null")
    void findUserByIdCase2(){
        UUID id = UUID.randomUUID();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserById(id);
        });
    }

    @Test
    @DisplayName("Should return User when user is found by login")
    void findUserByLoginCase1(){
        var login = "leo95";
        User user = new User();
        user.setLogin(login);
        user.setName("Leo");

        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        User result = userService.findUserByLogin(login);

        verify(userRepository).findByLogin(login);

        assertEquals(login, result.getLogin());

    }

    @Test
    @DisplayName("Should return ResourceNotFoundException when return is null")
    void findUserByLoginCase2(){
        when(userRepository.findByLogin(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserByLogin(anyString());
        });

        verify(userRepository).findByLogin(anyString());

    }


}
