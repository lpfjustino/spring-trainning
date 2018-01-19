package com.example.springtrainning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.springtrainning.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User findByLoginAndPassword(String login, String password);
  User findById(String id);
}