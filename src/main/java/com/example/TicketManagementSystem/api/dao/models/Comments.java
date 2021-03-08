package com.example.TicketManagementSystem.api.dao.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private int userId;
    private String comment;


    //Many comments belongs to one ticket
    @ManyToOne
    private Ticket ticket;


    public Comments(int commentId, int userId, String comment, Ticket ticket) {
        super();
        this.commentId = commentId;
        this.userId = userId;
        this.comment = comment;
        this.ticket = ticket;
    }


    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }


    public int getCommentId() {
        return commentId;
    }


    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public Ticket getTicket() {
        return ticket;
    }


    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

