package com.client_control.client_control.entities;

import com.client_control.client_control.entities.commom.AuditableEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_payment")
public class Payment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal value;
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "sign_id", nullable = false)
    private Sign sign;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Payment(){}

    public Payment(BigDecimal value, String description, Client client, Sign sign){
        this.value = value;
        this.description = description;
        this.client = client;
        this.sign = sign;
    }

    public Payment(BigDecimal value, String description, Client client, Sign sign, User user){
        this.value = value;
        this.description = description;
        this.client = client;
        this.sign = sign;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public User getUser() {
        return  user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
