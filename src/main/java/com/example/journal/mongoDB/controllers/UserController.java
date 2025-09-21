package com.example.journal.mongoDB.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import com.example.journal.mongoDB.models.User;
import com.example.journal.mongoDB.repositry.UserRepository;

import io.jsonwebtoken.Jwts;


import java.util.Date;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class UserController {


    @Autowired
    private UserRepository userRepository ;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String JWT_SECRET = "mySuperSecretKey1234567890123456"; // 32+ chars
    private final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());



    public ResponseEntity<String>  saveEntry(User user){
       
        // userRepository.save(user) ;

        try {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save user: "+ e.getMessage());
        }
    }

    public Optional<User> getUserById(ObjectId id){
        return userRepository.findById(id) ;
    }

    public List<User> getAll(){
        return userRepository.findAll() ;
    }

    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName).orElse(null) ;
    }

    public ResponseEntity<?> register (User user){
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        // Hash password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
    
    public ResponseEntity<?> login(User user){
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());

        if (existingUser.isEmpty()) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        // Check password
        if (!passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        // Generate JWT Token
        String token = Jwts.builder()
                        .setSubject(existingUser.get().getUserName())
                        .setId(existingUser.get().getId().toString())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact();


        return ResponseEntity.ok(token);
    }
}
