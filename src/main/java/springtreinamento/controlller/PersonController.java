package springtreinamento.controlller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import springtreinamento.Application;
import springtreinamento.entity.Person;
import springtreinamento.entity.Session;
import springtreinamento.entity.User;
import springtreinamento.repository.PersonRepository;
import springtreinamento.repository.SessionRepository;
import springtreinamento.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/person")
public class PersonController {
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private SessionRepository sessionRepository;
  @Autowired
  private UserRepository userRepository;

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public User authenticate(String token) {
    if(token.isEmpty())
      return null;

    Session userSession = sessionRepository.findByToken(token);

    if(userSession == null)
      return null;

    User loggedUser = userRepository.findById(userSession.getUserId());
    return loggedUser;
  }

  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewPerson (@Valid @RequestBody Person person, @RequestHeader("Authorization") String token) {
    if(authenticate(token) == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Isteji logano");
    }
    try {
      personRepository.save(person);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping
  public @ResponseBody ResponseEntity<?> updatePerson (@Valid @RequestBody Person person, @RequestHeader("Authorization") String token) {
    if(authenticate(token) == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Isteji logano");
    }
    try {
      return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping
  public @ResponseBody ResponseEntity<?> deletePerson (@Valid @RequestBody Person person, @RequestHeader("Authorization") String token) {
    if(authenticate(token) == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Isteji logano");
    }
    try {
      personRepository.delete(person);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPersonById(@PathVariable(value = "id") String personId, @RequestHeader("Authorization") String token) {
    if(authenticate(token) == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Isteji logano");
    }
    Person person = personRepository.findOne(personId);
    if(person == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(person);
  }

  @GetMapping("/")
  public ResponseEntity<?> getPersonByName(@RequestParam(value = "name") String personName, @RequestHeader("Authorization") String token) {
    if(authenticate(token) == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Isteji logano");
    }
    List<Person> person = personRepository.findByName(personName);
    if(person == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(person);
  }

  @GetMapping(path="/all")
  public @ResponseBody ResponseEntity<?> getAllPersons(@RequestHeader("Authorization") String token) {
    if(authenticate(token) == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Isteji logano");
    }
    return ResponseEntity.ok().body(personRepository.findAll());
  }
}