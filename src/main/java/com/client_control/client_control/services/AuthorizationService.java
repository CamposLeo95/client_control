package com.client_control.client_control.services;

import com.client_control.client_control.entities.UserDetailsImpl;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByLogin(username).orElseThrow(
                () -> new ResourceNotFoundException("Usuario não encontrado!")
        );

        return new UserDetailsImpl(user);
    }
}
