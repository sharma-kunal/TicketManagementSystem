package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.UserEntity;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserEntity userEntity) {
        List<UserEntity> users= userRepository.findAll();
        for (UserEntity user: users) {
            if (user.getEmail().equals(userEntity.getEmail())) {
                if (user.getPassword().equals(userEntity.getPassword())) {
                    return userEntity.getEmail();
                } else {
                    return "Email and Password Does Not Match";
                }
            }
        }
        return "User Does Not Exist";
    }
}
