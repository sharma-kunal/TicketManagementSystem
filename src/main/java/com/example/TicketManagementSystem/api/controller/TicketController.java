package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.Ticket;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<Ticket> createTicket(@RequestHeader("email") String email, @RequestBody Ticket ticket) throws ParseException {
        // check if request is from User

        List<User> users = userRepository.findAll();
        for (User user: users) {
            if (user.getEmail().equals(email) && user.getType().equals(EnUserType.USER)) {
                // create the ticket
                Date date = new Date();
                String curr_date = new SimpleDateFormat("dd/MM/yyyy").format(date);
                ticket.setStatus(EnStatusType.OPEN);
                ticket.setPriority(EnPriorityType.MEDIUM);
                ticket.setCreatedByUserId(user.getUserId());
                ticket.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(curr_date));
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 7);
                String dueDate = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                ticket.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(dueDate));
//                ticket.setCategory();
//                ticket.setUser();
                ticketRepository.save(ticket);
                return new ResponseEntity<>(ticket, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(new Ticket(), HttpStatus.UNAUTHORIZED);
    }
}
