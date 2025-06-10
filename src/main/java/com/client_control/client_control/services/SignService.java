package com.client_control.client_control.services;

import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.repositories.SignRepository;
import org.springframework.stereotype.Service;

@Service
public class SignService {

    private final SignRepository signRepository;

    public SignService(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    public void createSign(Sign sign){
        signRepository.save(sign);
    }
}
