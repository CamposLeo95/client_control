package com.client_control.client_control.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name = "tb_service_offering")
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    public ServiceOffering(){}

    public ServiceOffering(String businessService, BigDecimal price){
        this.name = businessService;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String businessService){
        this.name = businessService;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }
}
