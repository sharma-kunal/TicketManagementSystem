package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.Ticket;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.*;
import com.example.TicketManagementSystem.api.services.TicketService;
import com.example.TicketManagementSystem.api.services.UserService;
import com.example.TicketManagementSystem.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @CrossOrigin(origins = Utils.CORS)
    @GetMapping("")
    public ResponseEntity<List<Ticket>> getTicket(@RequestHeader("email") String email) {
        User user = userService.getUserByEmail(email);
        List<Ticket> result;
        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        } else if (user.getType().equals(EnUserType.USER)) {
            // if Type == User, then show ticket which are opened by that User
            result = ticketService.getTicketCreatedByUser(user);
        } else if (user.getType().equals(EnUserType.MEMBER)) {
            // if Type == Member, then show tickets which are in same category
            // Ticket service -> getTicketWithSameCategory()
            result = new ArrayList<>();
        } else {
            // if type == Admin, then show all tickets
            result = ticketRepository.findAll();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin(origins = Utils.CORS)
    @PostMapping("")
    public ResponseEntity<Ticket> createTicket(@RequestHeader("email") String email, @RequestBody Ticket ticket) throws ParseException {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(new Ticket(), HttpStatus.NOT_FOUND);
        }

        Ticket created_ticket = ticketService.createTicket(user, ticket);

        ticketRepository.save(created_ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = Utils.CORS)
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketByID(@PathVariable Integer id) {
        Ticket ticket = ticketService.findByID(id);
        if (ticket == null) {
            return new ResponseEntity<>(new Ticket(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }
}
