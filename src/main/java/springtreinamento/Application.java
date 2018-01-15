package springtreinamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springtreinamento.entity.Person;
import springtreinamento.repository.PersonRepository;


@SpringBootApplication
public class Application{

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

//  @Bean
//  public CommandLineRunner demo(PersonRepository repository) {
//    return (args) -> {
//      // save a couple of customers
//      repository.save(new Person(123l, "A", "Ta"));
//      repository.save(new Person(123l, "Pode", "Pa"));
//
//      // fetch all customers
//      log.info("Persons found with findAll():");
//      log.info("-------------------------------");
//      for (Person customer : repository.findAll()) {
//        log.info(customer.toString());
//      }
//      log.info("");
//    };
//  }
}
