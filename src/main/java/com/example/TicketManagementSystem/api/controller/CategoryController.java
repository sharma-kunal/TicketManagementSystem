package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.Category;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.CategoryRepository;
import com.example.TicketManagementSystem.api.repository.EnUserType;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import com.example.TicketManagementSystem.api.services.UserService;
import com.example.TicketManagementSystem.api.util.Utils;
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

    @CrossOrigin(origins = Utils.CORS)
    @GetMapping("")
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @CrossOrigin(origins = Utils.CORS)
    @PostMapping("")
    public Category createCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return category;
    }
}
