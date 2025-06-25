package com.client_control.client_control.services;

import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingRequestDTO;
import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingResponseDTO;
import com.client_control.client_control.entities.*;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.ServiceOfferingMapper;
import com.client_control.client_control.repositories.ServiceOfferingRepository;
import com.client_control.client_control.utils.SpecificationUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;
    private  final UserService userService;

    public ServiceOfferingService(ServiceOfferingRepository serviceOfferingRepository, UserService userService) {
        this.serviceOfferingRepository = serviceOfferingRepository;
        this.userService = userService;
    }

    public ServiceOffering findServiceOfferingById(Long id){
        return serviceOfferingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("serviço não encontrado!")
        );
    }

    public void createServiceOffering(ServiceOfferingRequestDTO dto){
        User user = userService.mySelf();
        serviceOfferingRepository.save(ServiceOfferingMapper.toEntity(dto, user));
    }

    public List<ServiceOfferingResponseDTO> findAllServiceOffering(Specification<ServiceOffering> specificationDto, Pageable pageable) {
        User user = userService.mySelf();
        Specification<ServiceOffering> specification = SpecificationUtils.SpecificationRole(specificationDto, user);

        return serviceOfferingRepository.findAll(specification, pageable)
                .stream()
                .map(ServiceOfferingMapper::toResponseDTO)
                .toList();
    }

}
