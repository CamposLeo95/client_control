package com.client_control.client_control.controllers;

import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.services.SignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService){
        this.signService = signService;
    }


    @PostMapping
    public ResponseEntity<Void> createSign(Sign sign){
        signService.createSign(sign);

        return ResponseEntity.ok().build();
    }
}
