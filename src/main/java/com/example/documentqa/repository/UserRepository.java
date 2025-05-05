package com.example.documentqa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.documentqa.entity.User;


    
    public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
}

