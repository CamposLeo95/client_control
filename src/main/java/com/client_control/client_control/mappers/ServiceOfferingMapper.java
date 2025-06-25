package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingRequestDTO;
import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingResponseDTO;
import com.client_control.client_control.entities.ServiceOffering;
import com.client_control.client_control.entities.User;

public class ServiceOfferingMapper {

    public static ServiceOfferingResponseDTO toResponseDTO(ServiceOffering serviceOffering){
        return new ServiceOfferingResponseDTO(
                serviceOffering.getId(),
                serviceOffering.getName(),
                serviceOffering.getPrice()
        );
    }

    public static ServiceOffering toEntity(ServiceOfferingRequestDTO dto, User user){
        ServiceOffering serviceOffering = new ServiceOffering(
                dto.name(),
                dto.price()
        );

        serviceOffering.setUser(user);

        return serviceOffering;
    }
}
