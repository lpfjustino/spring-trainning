package com.example.springtrainning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springtrainning.entity.Person;
import com.example.springtrainning.service.PersonService;
import com.example.springtrainning.service.SessionService;
import com.example.springtrainning.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/person")
public class PersonController {
  @Autowired
  private PersonService personService;

  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewPerson (@Valid @RequestBody Person person, @RequestHeader("Authorization") String token) {
    try {
      Person newPerson = personService.addNewPerson(person, token);
      if(newPerson == null) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }

      return new ResponseEntity<>(newPerson, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping
  public @ResponseBody ResponseEntity<?> updatePerson (@Valid @RequestBody Person person, @RequestHeader("Authorization") String token) {
    try {
      Person updatedPerson = personService.updatePerson(person, token);
      if(updatedPerson == null) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }
      return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping
  public @ResponseBody ResponseEntity<?> deletePerson (@Valid @RequestBody Person person, @RequestHeader("Authorization") String token) {
    try {
      personService.deletePerson(person, token);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPersonById(@PathVariable(value = "id") String personId, @RequestHeader("Authorization") String token) {
    // TODO: SOLVE NULL RETURN DILLEMA
    Person thePerson = personService.getPersonById(personId, token);
    if(thePerson == null) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    if(thePerson == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(thePerson);
  }

  @GetMapping("/")
  public ResponseEntity<?> getPersonByName(@RequestParam(value = "name") String personName, @RequestHeader("Authorization") String token) {
    Iterable<Person> people = personService.getPersonByName(personName, token);
    if(people == null) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    if(people == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(people);
  }

  @GetMapping(path="/all")
  public @ResponseBody ResponseEntity<?> getAllPersons(@RequestHeader("Authorization") String token) {
    Iterable<Person> people = personService.getAllPersons(token);
    if(people == null) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    if(people == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(people);
  }
}