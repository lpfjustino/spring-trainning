package com.example.springtrainning.service;

import com.example.springtrainning.entity.Person;
import com.example.springtrainning.entity.Session;
import com.example.springtrainning.entity.User;
import com.example.springtrainning.repository.PersonRepository;
import com.example.springtrainning.repository.SessionRepository;
import com.example.springtrainning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.ServiceMode;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private SessionRepository sessionRepository;
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SessionService sessionService;
  @Autowired
  private UserService userService;


  public User authenticate(String token) {
    Session userSession = sessionService.getUserSession(token);

    if(userSession == null)
      return null;

    boolean hasExpired = userSession.hasExpired();

    if(hasExpired)
      return null;

    User loggedUser = userService.getUserById(userSession.getUserId());
    return loggedUser;
  }

  public Person addNewPerson (Person person, String token) {
    if(authenticate(token) == null) {
      return null;
    }
    return personRepository.save(person);
  }

  public Person updatePerson (Person person, String token) {
    if(authenticate(token) == null) {
      return null;
    }
    return personRepository.save(person);
  }

  public void deletePerson (Person person, String token) {
    if(authenticate(token) == null) {
      return;
    }

    personRepository.delete(person);
  }

  public void deletePerson (String id, String token) {
    if(authenticate(token) == null) {
      return;
    }

    Person deletedPerson = personRepository.findById(id);
    personRepository.delete(deletedPerson);
  }

  public Person getPersonById(String personId, String token) {
    if(authenticate(token) == null) {
      return null;
    }

    return personRepository.findOne(personId);
  }

  public Iterable<Person> getPersonByName(String personName, String token) {
    if(authenticate(token) == null) {
      return null;
    }

    return personRepository.findByName(personName);
  }

  public Iterable<Person> getAllPersons(String token) {
    if(authenticate(token) == null) {
      return null;
    }
    return personRepository.findAll();
  }
}