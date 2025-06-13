package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.user.UserRequestDTO;
import com.client_control.client_control.dtos.user.UserResponseDTO;
import com.client_control.client_control.entities.User;

public class UserMapper {

    public static UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.isAdmin()
        );
    }

    public static User toEntity(UserRequestDTO dto){
        return new User(
                dto.name(),
                dto.email(),
                dto.login(),
                dto.password(),
                dto.admin()
        );
    }
}
