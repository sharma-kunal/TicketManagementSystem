package com.example.TicketManagementSystem.database.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.TicketManagementSystem.database.model.User.type;

public class Manager {
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        
        User u1 = new User();
        //u1.setUserId(100);
        u1.setName("Govind");
        u1.setEmail("abc@gmail.com");
        u1.setPassword("abc");
        u1.setUserType(type.Admin);
        
        User u2 = new User();
        //u2.setUserId(101);
        u2.setName("Kunal");
        u2.setEmail("abc@gmail.com");
        u2.setPassword("abc");
        u2.setUserType(type.User);
        
        User u3 = new User();
        //u3.setUserId(102);
        u3.setName("Sai");
        u3.setEmail("abc@gmail.com");
        u3.setPassword("abc");
        u3.setUserType(type.Member);
        
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        s.save(u1);
        s.save(u2);
        s.save(u3);
        
        tx.commit();
        s.close();
        factory.close();
	}
}
