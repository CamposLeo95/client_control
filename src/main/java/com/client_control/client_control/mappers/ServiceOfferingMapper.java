package com.client_control.client_control.mappers;

import com.client_control.client_control.dtos.serviceOffering.ServiceOfferingRequestDTO;
import com.client_control.client_control.entities.ServiceOffering;

public class ServiceOfferingMapper {

    public static ServiceOffering toEntity(ServiceOfferingRequestDTO dto){
        return new ServiceOffering(
                dto.name(),
                dto.price()
        );
    }
}
