package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingRequestDTO;
import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingResponseDTO;
import com.client_control.client_control.entities.ServiceOffering;
import com.client_control.client_control.mappers.ServiceOfferingMapper;
import com.client_control.client_control.services.ServiceOfferingService;
import com.client_control.client_control.specifications.SpecificationServiceOfferingTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-offerring")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    public ServiceOfferingController(ServiceOfferingService serviceOfferingService){
        this.serviceOfferingService=serviceOfferingService;
    }

    @PostMapping
    public ResponseEntity<Void> createServiceOffering(@RequestBody ServiceOfferingRequestDTO dto){
        serviceOfferingService.createServiceOffering(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceOfferingResponseDTO>> findAllServiceOffering(
            SpecificationServiceOfferingTemplate specification,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(serviceOfferingService.findAllServiceOffering(specification, pageable));
    }

    @GetMapping("/{service_id}")
    public ResponseEntity<ServiceOfferingResponseDTO> findServiceOfferingById(@PathVariable("service_id") Long id ) {
        ServiceOffering service =  serviceOfferingService.findServiceOfferingById(id);
        return ResponseEntity.ok(ServiceOfferingMapper.toResponseDTO(service));
    }
}
