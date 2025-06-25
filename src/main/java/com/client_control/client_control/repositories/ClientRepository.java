package com.client_control.client_control.repositories;

import com.client_control.client_control.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByLogin(String login);
    Optional<Client> findByEmailAndUserId(String email, UUID userId);
    Optional<Client> findByLoginAndUserId(String email, UUID userId);
}
