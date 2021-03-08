package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.Category;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.CategoryRepository;
import com.example.TicketManagementSystem.api.repository.EnUserType;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestHeader("email") String email, @RequestBody Category category) {
        // check if user is Admin
        List<User> users = userRepository.findAll();
        for (User user: users) {
            if (user.getEmail().equals(email) && user.getType().equals(EnUserType.ADMIN)) {
                categoryRepository.save(category);
                return new ResponseEntity<>(category, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Category(), HttpStatus.FORBIDDEN);
    }
}
