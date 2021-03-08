package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
