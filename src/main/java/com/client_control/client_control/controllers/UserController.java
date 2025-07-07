package com.client_control.client_control.controllers;


import com.client_control.client_control.dtos.user.RecoveryPasswordRequestDTO;
import com.client_control.client_control.dtos.user.UpdatePasswordRequestDTO;
import com.client_control.client_control.dtos.user.UserRequestDTO;
import com.client_control.client_control.dtos.user.UserResponseDTO;
import com.client_control.client_control.mappers.UserMapper;
import com.client_control.client_control.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me() {
        return ResponseEntity.ok(UserMapper.toResponseDTO(userService.mySelf()));
    }


    @PutMapping("/update-password/{token}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable("token") String token, @RequestBody UpdatePasswordRequestDTO dto){
        userService.updateUserPassword(token, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recovery-password")
    public ResponseEntity<Void> recoveryPassword(@RequestBody RecoveryPasswordRequestDTO dto) {
        userService.recoveryPassword(dto);
        return ResponseEntity.ok().build();
    }

}
