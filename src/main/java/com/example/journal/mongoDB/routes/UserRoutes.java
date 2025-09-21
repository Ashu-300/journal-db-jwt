package com.example.journal.mongoDB.routes;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journal.mongoDB.controllers.UserController;

import com.example.journal.mongoDB.models.User;

@RequestMapping("/user")
@RestController
public class UserRoutes {
    
    @Autowired
    private UserController userController ;

     @GetMapping("")
    public List<User> getAll(){

        return userController.getAll() ;
    }

    @PostMapping("")
    public ResponseEntity<String> makeEntry(@RequestBody User user){
        return userController.saveEntry(user);
       
    }

    @GetMapping("/{id}")
    public User getEntryById(@PathVariable ObjectId id ){
       return userController.getUserById(id).orElse(null) ;
    }

     @PostMapping("/register")
     public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userController.register(user) ;
     }

     @PostMapping("/login")
     public ResponseEntity<?> loginUser (@RequestBody User user) {
        return userController.login(user) ;
     }
}
