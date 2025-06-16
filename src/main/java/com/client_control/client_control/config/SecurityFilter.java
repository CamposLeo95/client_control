package com.client_control.client_control.config;

import com.client_control.client_control.entities.User;
import com.client_control.client_control.entities.UserDetailsImpl;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.repositories.UserRepository;
import com.client_control.client_control.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoveryToken(request);
        if(token != null){
            var login = tokenService.validateToken(token);
            var user = userRepository.findByLogin(login).orElseThrow(
                    () -> new ResourceNotFoundException("Usuario não encontrado!")
            );

            var userDetails = new UserDetailsImpl(user);

            var authentication = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
