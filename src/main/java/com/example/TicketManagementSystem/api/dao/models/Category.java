package com.example.TicketManagementSystem.api.dao.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    private String name;

    //In one category their can be many groups
    @OneToMany(mappedBy = "category")
    private List<Groups> groups;


    //In one category their can be many tickets assigned
    @OneToMany(mappedBy = "category")
    private List<Ticket> ticket;


    public Category(int categoryId, String name, List<Groups> groups, List<Ticket> ticket) {
        super();
        this.categoryId = categoryId;
        this.name = name;
        this.groups = groups;
        this.ticket = ticket;
    }


    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }


    public int getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Groups> getGroups() {
        return groups;
    }


    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }


    public List<Ticket> getTicket() {
        return ticket;
    }


    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }





}
