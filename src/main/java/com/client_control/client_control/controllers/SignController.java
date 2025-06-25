package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.sign.SignResponseDTO;
import com.client_control.client_control.services.SignService;
import com.client_control.client_control.specifications.SpecificationSignTemplate;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<SignResponseDTO>> findAllSign(SpecificationSignTemplate specificationSignDTO, Pageable pageable) {
        return ResponseEntity.ok(signService.findAllSign(specificationSignDTO, pageable));
    }


    @PutMapping("/toggle/{sign_id}")
    public ResponseEntity<Void> toggleStatusSign(@PathVariable("sign_id") Long id) {
        signService.toggleStatusSign(id);
        return ResponseEntity.ok().build();
    }
}
