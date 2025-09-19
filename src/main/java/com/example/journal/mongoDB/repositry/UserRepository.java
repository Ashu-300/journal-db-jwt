package com.example.journal.mongoDB.repositry;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.journal.mongoDB.models.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUserName(String userName);
}
