package com.client_control.client_control.controllers;


import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.services.ClientService;
import com.client_control.client_control.specifications.SpecificationClientTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        clientService.createClient(dto, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClient(SpecificationClientTemplate specificationClient){
        return ResponseEntity.ok(clientService.getAllClient(specificationClient));
    }
}
