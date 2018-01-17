package springtreinamento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springtreinamento.entity.Person;
import springtreinamento.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  public User findByLoginAndPassword(String login, String password);
}