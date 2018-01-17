package springtreinamento.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springtreinamento.entity.Session;
import springtreinamento.repository.SessionRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/session")
public class SessionController {
  @Autowired
  private SessionRepository sessionRepository;

  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewSession (@Valid @RequestBody Session session) {
    try {
      sessionRepository.save(session);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping
  public @ResponseBody ResponseEntity<?> updateSession (@Valid @RequestBody Session session) {
    try {
      return new ResponseEntity<>(sessionRepository.save(session), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping
  public @ResponseBody ResponseEntity<?> deleteSession (@Valid @RequestBody Session session) {
    try {
      sessionRepository.delete(session);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Session> getSessionById(@PathVariable(value = "id") String sessionId) {
    Session session = sessionRepository.findOne(sessionId);
    if(session == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(session);
  }

  @GetMapping(path="/all")
  public @ResponseBody ResponseEntity<Iterable<Session>> getAllSessions() {
    return ResponseEntity.ok().body(sessionRepository.findAll());
  }
}