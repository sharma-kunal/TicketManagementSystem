package com.example.TicketManagementSystem.api.dao;

import com.example.TicketManagementSystem.api.repository.EnUserType;

public class User {
    private int userId;
    private String name;

    public User(int userId, String name, String email, EnUserType type) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.type = type;
    }



    private String email;
    private EnUserType type;


}
