package springtreinamento.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import springtreinamento.entity.Person;
import springtreinamento.repository.PersonRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/person")
public class PersonController {
  @Autowired
  private PersonRepository personRepository;

  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewPerson (@Valid @RequestBody Person person) {
    try {
      personRepository.save(person);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping
  public @ResponseBody ResponseEntity<?> updatePerson (@Valid @RequestBody Person person) {
    try {
      return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping
  public @ResponseBody ResponseEntity<?> deletePerson (@Valid @RequestBody Person person) {
    try {
      personRepository.delete(person);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") String personId) {
    Person person = personRepository.findOne(personId);
    if(person == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(person);
  }

  @GetMapping("/")
  public ResponseEntity<List<Person>> getPersonByName(@RequestParam(value = "name") String personName) {
    List<Person> person = personRepository.findByName(personName);
    if(person == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(person);
  }

  @GetMapping(path="/all")
  public @ResponseBody ResponseEntity<Iterable<Person>> getAllPersons() {
    return ResponseEntity.ok().body(personRepository.findAll());
  }
}