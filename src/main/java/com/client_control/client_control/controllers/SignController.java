package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.sign.SignResponseDTO;
import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.mappers.SignMapper;
import com.client_control.client_control.services.SignService;
import com.client_control.client_control.specifications.SpecificationSignTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<SignResponseDTO>> findAllSign(
            SpecificationSignTemplate specificationSignDTO,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(signService.findAllSign(specificationSignDTO, pageable));
    }


    @PutMapping("/toggle/{sign_id}")
    public ResponseEntity<Void> toggleStatusSign(@PathVariable("sign_id") Long id) {
        signService.toggleStatusSign(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{sign_id}")
    public ResponseEntity<SignResponseDTO> findSignById(@PathVariable("sign_id") Long id){
        Sign sign = signService.findSignById(id);
        return ResponseEntity.ok(SignMapper.toResponseDTO(sign));
    }
}
