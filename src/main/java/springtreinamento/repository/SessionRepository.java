package springtreinamento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springtreinamento.entity.Session;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {
  Session findByToken(String token);
  Long deleteByUserId(String userId);
}