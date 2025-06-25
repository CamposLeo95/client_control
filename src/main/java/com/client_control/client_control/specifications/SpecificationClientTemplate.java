package com.client_control.client_control.specifications;

import com.client_control.client_control.entities.Client;

import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({ @Spec(path = "name", spec = LikeIgnoreCase.class) })
public interface SpecificationClientTemplate extends Specification<Client> {}
