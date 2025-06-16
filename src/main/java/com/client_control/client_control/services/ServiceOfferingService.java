package com.client_control.client_control.services;

import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingRequestDTO;
import com.client_control.client_control.entities.ServiceOffering;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.ServiceOfferingMapper;
import com.client_control.client_control.repositories.ServiceOfferingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    public ServiceOfferingService(ServiceOfferingRepository serviceOfferingRepository) {
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public ServiceOffering findServiceOfferingById(Long id){
        return serviceOfferingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("serviço não encontrado!")
        );
    }

    public void createServiceOffering(ServiceOfferingRequestDTO dto){
        serviceOfferingRepository.save(ServiceOfferingMapper.toEntity(dto));
    }

    public List<ServiceOffering> findAllServiceOffering() {
        return serviceOfferingRepository.findAll();
    }

}
