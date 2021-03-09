package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.*;
import com.example.TicketManagementSystem.api.repository.*;
import com.example.TicketManagementSystem.api.services.AttachmentService;
import com.example.TicketManagementSystem.api.services.CommentService;
import com.example.TicketManagementSystem.api.services.TicketService;
import com.example.TicketManagementSystem.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    AttachmentService attachmentService;

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
            result = ticketService.getTicketWithSameCategory(user);
        } else {
            // if type == Admin, then show all tickets
            result = ticketRepository.findAll();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketByID(@PathVariable Integer id) {
        Ticket ticket = ticketService.findByID(id);
        if (ticket == null) {
            return new ResponseEntity<>(new Ticket(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @PutMapping("/{id}/comment")
    public ResponseEntity<String> createComment(@RequestHeader("email") String email, @PathVariable Integer id, @RequestBody Comments comment) {
        User user = userService.getUserByEmail(email);
        Ticket ticket = ticketService.findByID(id);
        if (user == null || ticket == null) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } else {
            // assuming request is from MEMBER
            System.out.println("-----------------------------------------------------");
            System.out.println(comment.getComment());
            System.out.println("-----------------------------------------------------");
            commentService.createComment(user, comment.getComment(), ticket);
            List<Comments> comments = commentService.getAllComments(ticket);
            ticket.setComments(comments);
            ticketRepository.save(ticket);
            return new ResponseEntity<>(comment.getComment(), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}/uploadAttachment")
    public void uploadAttachment(@RequestHeader("email") String email, @PathVariable Integer id,
                                 @RequestBody MultipartFile file) {
        System.out.println("Function Called");
        System.out.println(file);
        User user = userService.getUserByEmail(email);
        Ticket ticket = ticketService.findByID(id);
        if (user != null && ticket != null) {
            attachmentService.saveAttachment(user, file, ticket);
        }
    }

    @GetMapping("downloadAttachment/{attachment_id}")
    public ResponseEntity<Resource> getAttachment(@PathVariable Integer attachment_id) {
        Attachments attachments = attachmentService.getFile(attachment_id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachments.getAttachmentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachments.getAttachmentName() + "\"")
                .body(new ByteArrayResource(attachments.getAttachment()));
    }
}
