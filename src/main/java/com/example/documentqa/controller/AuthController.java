package com.example.documentqa.controller;

import com.example.documentqa.entity.User;
import com.example.documentqa.repository.UserRepository;
import com.example.documentqa.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Ensure the password is encoded
        System.out.println("Encoded password: " + user.getPassword());  // Log the encoded password
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

   

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginData) {
        System.out.println("Login attempt for username: " + loginData.getUsername()); // Log the incoming username
        
        User user = userRepository.findByUsername(loginData.getUsername());
        
        if (user != null) {
            System.out.println("User found: " + user.getUsername()); // Log the found user
            
            // Log the password match attempt
            if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
                String token = jwtUtil.createJwt(user.getUsername());
                System.out.println("Generated token: " + token); // Log generated token
                return ResponseEntity.ok(token);
            } else {
                System.out.println("Password mismatch for username: " + loginData.getUsername());
            }
        } else {
            System.out.println("User not found: " + loginData.getUsername());
        }
        
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    }

