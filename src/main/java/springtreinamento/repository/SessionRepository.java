package springtreinamento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springtreinamento.entity.Session;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {
  public Session findByToken(String token);
}