package com.client_control.client_control.dtos.serviceOffering;

import com.client_control.client_control.entities.ServiceOffering;

import java.math.BigDecimal;

public class ServiceOfferingDTO {

    public record RecordServiceOfferingDTO(Long id, String name, BigDecimal price){}

    public static RecordServiceOfferingDTO toServiceOfferingDTO(ServiceOffering serviceOffering){
        return new RecordServiceOfferingDTO(
                serviceOffering.getId(),
                serviceOffering.getName(),
                serviceOffering.getPrice()
        );
    }
}
