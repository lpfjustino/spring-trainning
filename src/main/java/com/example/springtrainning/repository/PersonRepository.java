package com.example.springtrainning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.springtrainning.entity.Person;
import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String>{
      List<Person> findByName(String name);
      List<Person> findByCountry(String country);
      Person findById(String id);
}