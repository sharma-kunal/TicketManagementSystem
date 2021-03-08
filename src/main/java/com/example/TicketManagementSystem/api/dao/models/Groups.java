package com.example.TicketManagementSystem.api.dao.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private String groupName;
    //Many groups belongs to one category
    @ManyToOne
    private Category category;
    //Many groups can have many users
    @ManyToMany(mappedBy = "groups")
    private List<User> user;
    public Groups(int groupId, String groupName, Category category, List<User> user) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.category = category;
        this.user = user;
    }
    public Groups() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<User> getUser() {
        return user;
    }
    public void setUser(List<User> user) {
        this.user = user;
    }



}

