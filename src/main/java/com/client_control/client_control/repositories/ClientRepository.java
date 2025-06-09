package com.client_control.client_control.repositories;
import com.client_control.client_control.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
