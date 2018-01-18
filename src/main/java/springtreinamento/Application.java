package springtreinamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import springtreinamento.entity.Person;
import springtreinamento.repository.PersonRepository;


@SpringBootApplication
@ComponentScan("springtreinamento")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class
})
@PropertySource({"classpath:application.properties"})
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
