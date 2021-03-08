package com.example.TicketManagementSystem.api.dao.models;

import com.example.TicketManagementSystem.api.repository.EnUserType;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnUserType type;

    @OneToMany(mappedBy = "user")
    private List<Ticket> ticket;

    @ManyToMany
    private List<Groups> groups;

    public User() { }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnUserType getType() {
        return type;
    }

    public void setType(EnUserType type) {
        this.type = type;
    }


    // for testing purposes
    public void printAllDetails() {
        System.out.println(this.userId);
        System.out.println(this.name);
        System.out.println(this.type);
        System.out.println(this.email);
        System.out.println(this.password);
    }
}
