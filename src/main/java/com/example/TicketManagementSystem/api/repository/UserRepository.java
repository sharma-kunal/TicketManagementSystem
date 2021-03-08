package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.User;
import com.example.TicketManagementSystem.api.dao.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
