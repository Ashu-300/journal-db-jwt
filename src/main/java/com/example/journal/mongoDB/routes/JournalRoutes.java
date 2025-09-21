package com.example.journal.mongoDB.routes;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journal.mongoDB.controllers.JournalController;
import com.example.journal.mongoDB.controllers.UserController;
import com.example.journal.mongoDB.models.JournalEntry;
import com.example.journal.mongoDB.models.User;

@RestController
@RequestMapping("/journal")
public class JournalRoutes {

    @Autowired
    private JournalController journalController ;
    // @Autowired
    // private UserController userController ;
    
    // @GetMapping("username/{userName}")
    // public ResponseEntity<?> getAllJourbEntriesOfUser(@PathVariable String userName){
    //    User user = userController.getUserByUserName(userName) ;
    //    List<JournalEntry> all = user.getJournalEntries() ;
    //    if(all != null && !all.isEmpty()){
    //     return new ResponseEntity<>(all , HttpStatus.OK) ;
    //    }
    //    return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    // }

    @PostMapping("")
    public boolean makeEntry(@RequestBody JournalEntry entry){
        journalController.saveEntry(entry);
        return true ;
    }

    @GetMapping("id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id ){
       return journalController.getEntryById(id).orElse(null) ;
    }
}
