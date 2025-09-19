package com.example.journal.mongoDB.models;

import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class JournalEntry {
    
    @Id
    ObjectId id ;
    String title ;
    String content ;
    LocalDateTime date ;

}
