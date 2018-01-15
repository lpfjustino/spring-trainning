package springtreinamento.controlller;

import org.springframework.beans.factory.annotation.Autowired;
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
  public @ResponseBody String addNewPerson (@Valid @RequestBody Person person) {
    manager.persist(person);
    return "Saved";
  }

  @Transactional
  @PutMapping
  public @ResponseBody String updatePerson (@Valid @RequestBody Person person) {
    manager.merge(person);
    return "Updated";
  }

  @GetMapping("/")
  public @ResponseBody List<Person> lista() {
    return manager.createQuery("select p from Person p").getResultList();
  }

  @GetMapping("/{id}")
  public Person getPersonById(@PathVariable(value="id") Long id) {
    return manager.find(Person.class, id);
  }

  @Transactional
  @DeleteMapping
  public @ResponseBody String remove(@Valid @RequestBody Person person) {
    Person removedPerson = getPersonById(person.getId());
    manager.remove(removedPerson);
    return "Removed";
  }

  @Transactional
  @DeleteMapping("/{id}")
  public @ResponseBody String remove(@PathVariable(value="id") Long id) {
    Person removedPerson = getPersonById(id);
    manager.remove(removedPerson);
    return "Removed";
  }
}
