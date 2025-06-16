package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.user.UserRequestDTO;
import com.client_control.client_control.dtos.user.UserResponseDTO;
import com.client_control.client_control.entities.ServiceOffering;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDTO dto) {
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("user_id")UUID id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }




}
