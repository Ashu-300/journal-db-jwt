package com.example.journal.mongoDB.repositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.journal.mongoDB.models.JournalEntry;


public interface JournalRepository extends MongoRepository< JournalEntry ,ObjectId> {

    
}
