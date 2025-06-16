package com.client_control.client_control.entities;

import com.client_control.client_control.entities.commom.AuditableEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_client")
public class Client extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany
    List<Sign> signs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = true)
    private User user;

    public Client() {}

    public Client(String name, String login, String password, String phone, String email) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {this.id = id;}

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() { return user; }
    public void setUser(User user) {this.user = user;}

    public List<Sign> getSigns() {return signs;}

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }
}
