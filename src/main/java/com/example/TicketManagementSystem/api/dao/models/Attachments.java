package com.example.TicketManagementSystem.api.dao.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attachmentId;
    private int userId;

    @Lob
    private byte[] attachment;

    // Many attachment belongs to one ticket
    @ManyToOne
    private Ticket ticket;

    public Attachments(int attachmentId, int userId, byte[] attachment, Ticket ticket) {
        super();
        this.attachmentId = attachmentId;
        this.userId = userId;
        this.attachment = attachment;
        this.ticket = ticket;
    }

    public Attachments() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }




}

