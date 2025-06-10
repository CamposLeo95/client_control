package com.client_control.client_control.controllers;


import com.client_control.client_control.dtos.client.ClientRequestDTO;
import com.client_control.client_control.dtos.client.ClientResponseDTO;
import com.client_control.client_control.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDTO dto){
        clientService.createClient(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClient(){
        return ResponseEntity.ok(clientService.getAllClient());
    }
}
