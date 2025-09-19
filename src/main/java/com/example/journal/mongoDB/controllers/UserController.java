package com.example.journal.mongoDB.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import com.example.journal.mongoDB.models.User;
import com.example.journal.mongoDB.repositry.UserRepository;

@Component
public class UserController {

    @Autowired
    private UserRepository userRepository ;

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

    

    
}
