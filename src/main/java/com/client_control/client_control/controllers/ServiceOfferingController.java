package com.client_control.client_control.controllers;

import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingRequestDTO;
import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingResponseDTO;
import com.client_control.client_control.entities.ServiceOffering;
import com.client_control.client_control.services.ServiceOfferingService;
import com.client_control.client_control.specifications.SpecificationServiceOfferingTemplate;
import org.springframework.data.domain.Pageable;
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
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceOfferingResponseDTO>> findAllServiceOffering(SpecificationServiceOfferingTemplate specification, Pageable pageable) {
        return ResponseEntity.ok(serviceOfferingService.findAllServiceOffering(specification, pageable));
    }
}
