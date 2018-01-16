package springtreinamento.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import springtreinamento.entity.Person;
import springtreinamento.repository.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/person")
public class PersonController {
  @PersistenceContext
  private EntityManager manager;

  @Transactional
  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewPerson (@Valid @RequestBody Person person) {
    try {
      manager.persist(person);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @Transactional
  @PutMapping
  public @ResponseBody ResponseEntity<?> updatePerson (@Valid @RequestBody Person person) {
    try {
      Person updated = manager.merge(person);
      return new ResponseEntity<>(updated, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/")
  public @ResponseBody ResponseEntity<?> lista() {
    try {
      List<Person> list = manager.createQuery("select p from Person p").getResultList();
      return new ResponseEntity<>(list, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPersonById(@PathVariable(value="id") Long id) {
    try {
      Person person = manager.find(Person.class, id);
      return new ResponseEntity<>(person, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @Transactional
  @DeleteMapping
  public @ResponseBody ResponseEntity<?> remove(@Valid @RequestBody Person person) {
    try {
      Person removedPerson = manager.find(Person.class, person.getId());
      manager.remove(removedPerson);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @Transactional
  @DeleteMapping("/{id}")
  public @ResponseBody ResponseEntity<?> remove(@PathVariable(value="id") Long id) {
    try {
      Person removedPerson = manager.find(Person.class, id);
      manager.remove(removedPerson);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
