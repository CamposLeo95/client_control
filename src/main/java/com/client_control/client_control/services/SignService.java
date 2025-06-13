package com.client_control.client_control.services;

import com.client_control.client_control.dtos.sign.SignUpdateRequestDTO;
import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.repositories.SignRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SignService {

    private final SignRepository signRepository;

    public SignService(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    public Sign findSignById(Long id){
        return signRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Assinatura nao encontrada!")
        );
    }

    public Sign createSign(Sign sign){
        return signRepository.save(sign);
    }

    public void updateSign(Sign sign, LocalDate expireDate) {
        var currentSign = signRepository.findById(sign.getId()).orElseThrow(
                () -> new RuntimeException("Assinatura nao encontrada!")
        );

        if(expireDate != null){
            currentSign.setExpireDate(expireDate);
        }

        signRepository.save(currentSign);

    }

    public void toggleStatusSign(Long id){
        var currentSign = findSignById(id);

        if(currentSign.isActiveSign()){
            currentSign.setActiveSign(false);
        } else {
            currentSign.setActiveSign(true);
        }

        signRepository.save(currentSign);

    }
}
