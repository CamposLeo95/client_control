package com.client_control.client_control.repositories;

import com.client_control.client_control.entities.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SignRepository extends JpaRepository<Sign, Long>, JpaSpecificationExecutor<Sign> {
}
