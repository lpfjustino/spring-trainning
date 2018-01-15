package springtreinamento.repository;

import org.springframework.data.repository.CrudRepository;
import springtreinamento.entity.Person;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>{
      List<Person> findByName(String name);
      List<Person> findByCountry(String country);
}