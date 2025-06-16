package com.client_control.client_control.services;

import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.repositories.SignRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SignService {

    private final SignRepository signRepository;

    public SignService(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    public Sign findSignById(Long id){
        return signRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Assinatura nao encontrada!")
        );
    }


    public List<Sign> findAllSign() {
        return signRepository.findAll();
    }

    public Sign createSign(Sign sign){
        return signRepository.save(sign);
    }

    public void updateSign(Sign sign, LocalDate expireDate) {
        var currentSign = signRepository.findById(sign.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Assinatura nao encontrada!")
        );

        if(expireDate != null){
            currentSign.setExpireDate(expireDate);
        }

        signRepository.save(currentSign);

    }

    public void toggleStatusSign(Long id){
        Sign sign = findSignById(id);

        sign.setActiveSign(!sign.isActiveSign());
        signRepository.save(sign);

    }
}
