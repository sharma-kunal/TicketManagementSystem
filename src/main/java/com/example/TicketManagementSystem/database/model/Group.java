package com.example.TicketManagementSystem.database.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Group {
	
	@Id
	private int groupId;
	private String groupName;
	private int categoryId;
	

}
