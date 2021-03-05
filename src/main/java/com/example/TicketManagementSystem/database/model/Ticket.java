package com.example.TicketManagementSystem.database.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
	
	@Id
	private int ticketId;
	private enum status{Open,close}
	private String title;
	private enum priority{High,Medium,Low}
	private Date startDate;
	private Date dueDate;
	private String description;
	private byte[] attachment;
	//private int use_id;
	//private int category_id;
	private String[] comments;
	
	
	

}
