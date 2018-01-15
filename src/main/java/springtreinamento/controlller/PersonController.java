package springtreinamento.controlller;

import org.springframework.beans.factory.annotation.Autowired;
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
  public @ResponseBody String addNewPerson (@Valid @RequestBody Person person) {
    personRepository.save(person);
    return "Saved";
  }

  @PutMapping
  public @ResponseBody String updatePerson (@Valid @RequestBody Person person) {
    personRepository.save(person);
    return "Updated";
  }

  @DeleteMapping
  public @ResponseBody String deletePerson (@Valid @RequestBody Person person) {
    personRepository.delete(person);
    return "Removed";
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
  public @ResponseBody Iterable<Person> getAllPersons() {
    return personRepository.findAll();
  }
}