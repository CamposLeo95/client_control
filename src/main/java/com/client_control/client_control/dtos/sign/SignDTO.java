package com.client_control.client_control.dtos.sign;

import com.client_control.client_control.entities.Sign;

import java.time.LocalDate;

public class SignDTO {

    public record RecordSignDTO(
            Long id,
            boolean activeSign,
            LocalDate expireDate
    ){}

    public static RecordSignDTO toSignDTO(Sign sign){
        return new RecordSignDTO(
                sign.getId(),
                sign.isActiveSign(),
                sign.getExpireDate()
        );
    }
}
