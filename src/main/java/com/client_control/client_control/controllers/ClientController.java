package com.client_control.client_control.controllers;


import com.client_control.client_control.config.SecurityFilter;
import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.entities.UserDetailsImpl;
import com.client_control.client_control.mappers.ClientMapper;
import com.client_control.client_control.services.ClientService;
import com.client_control.client_control.specifications.SpecificationClientTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDTO dto) {
        clientService.createClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAllClient(
            SpecificationClientTemplate specificationClient,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.ok(clientService.findAllClient(specificationClient, pageable));
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<ClientResponseDTO> findClientById(@PathVariable("client_id") Long id ){
        ClientMapper.toResponseDTO(clientService.findClientById(id));
        return ResponseEntity.ok(ClientMapper.toResponseDTO(clientService.findClientById(id)));
    }
}
