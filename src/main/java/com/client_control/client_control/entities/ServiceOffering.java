package com.client_control.client_control.entities;

import com.client_control.client_control.entities.commom.AuditableEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_service_offering")
public class ServiceOffering extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    List<Sign> signs = new ArrayList<>();

    public ServiceOffering(){}

    public ServiceOffering(String businessService, BigDecimal price){
        this.name = businessService;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String businessService){
        this.name = businessService;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public List<Sign> getSigns(){
        return signs;
    }

    public void setSigns(List<Sign> signs){
        this.signs = signs;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

}
