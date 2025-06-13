package com.client_control.client_control.controllers;

import com.client_control.client_control.services.SignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService){
        this.signService = signService;
    }


    @PutMapping("/toggle/{sign_id}")
    public ResponseEntity<Void> toggleStatusSign(@PathVariable("sign_id") Long id) {
        signService.toggleStatusSign(id);

        return ResponseEntity.ok().build();
    }
}
