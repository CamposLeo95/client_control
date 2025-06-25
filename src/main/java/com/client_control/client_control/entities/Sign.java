package com.client_control.client_control.entities;

import com.client_control.client_control.entities.commom.AuditableEntity;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "tb_sign")
public class Sign extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active")
    private boolean activeSign;

    private LocalDate expireDate;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_offering_id", nullable = false)
    private ServiceOffering serviceOffering;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Sign() {}

    public Sign(boolean activeSign, LocalDate expireDate, Client client, ServiceOffering serviceOffering) {
        this.activeSign = activeSign;
        this.expireDate = expireDate;
        this.client = client;
        this.serviceOffering = serviceOffering;
    }

    public Sign(boolean activeSign, LocalDate expireDate, Client client, ServiceOffering serviceOffering, User user) {
        this.activeSign = activeSign;
        this.expireDate = expireDate;
        this.client = client;
        this.serviceOffering = serviceOffering;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActiveSign() {
        return activeSign;
    }

    public void setActiveSign(boolean active) {
        this.activeSign = active;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ServiceOffering getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(ServiceOffering serviceOffering) {
        this.serviceOffering = serviceOffering;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
