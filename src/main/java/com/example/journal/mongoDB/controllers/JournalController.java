package com.example.journal.mongoDB.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.journal.mongoDB.models.JournalEntry;
import com.example.journal.mongoDB.repositry.JournalRepository;

@Component
public class JournalController {
    @Autowired
    private JournalRepository journalRepository ;
   

    public void saveEntry(JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now()) ;
        journalRepository.save(myEntry) ;
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalRepository.findById(id) ;
    }

    public List<JournalEntry> getAll(){
        return journalRepository.findAll() ;
    }

}
