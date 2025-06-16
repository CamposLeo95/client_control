package com.client_control.client_control.controllers;

import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.services.SignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService){
        this.signService = signService;
    }

    @GetMapping
    public ResponseEntity<List<Sign>> findAllSign() {
        return ResponseEntity.ok(signService.findAllSign());
    }


    @PutMapping("/toggle/{sign_id}")
    public ResponseEntity<Void> toggleStatusSign(@PathVariable("sign_id") Long id) {
        signService.toggleStatusSign(id);
        return ResponseEntity.ok().build();
    }
}
