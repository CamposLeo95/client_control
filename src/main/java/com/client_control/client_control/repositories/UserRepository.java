package com.client_control.client_control.repositories;

import com.client_control.client_control.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
}
