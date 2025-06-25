package com.client_control.client_control.dtos.user;

import com.client_control.client_control.entities.User;

public class UserDTO {

    public record RecordUserDTO(){}

    public static RecordUserDTO toUserDTO(User user){
        return  new RecordUserDTO();
    }
}
