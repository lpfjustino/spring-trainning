package springtreinamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springtreinamento.entity.Person;
import springtreinamento.repository.PersonRepository;


@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

//	public void run(String... args) throws Exception {
//
//		repository.deleteAll();
//
//		// save a couple of persons
//		repository.save(new Person("Alice", "Smith"));
//		repository.save(new Person("Bob", "Smith"));
//
//		// fetch all persons
//		System.out.println("Persons found with findAll():");
//		System.out.println("-------------------------------");
//		for (Person person : repository.findAll()) {
//			System.out.println(person);
//		}
//		System.out.println();
//
//		// fetch an individual person
//		System.out.println("Person found with findByFirstName('Alice'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByName("Alice"));
//
//		System.out.println("Persons found with findByLastName('Smith'):");
//		System.out.println("--------------------------------");
//		for (Person person : repository.findByCountry("Smith")) {
//			System.out.println(person);
//		}
//
//	}
}
