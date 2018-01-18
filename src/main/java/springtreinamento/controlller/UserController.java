package springtreinamento.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springtreinamento.entity.Session;
import springtreinamento.entity.User;
import springtreinamento.repository.SessionRepository;
import springtreinamento.repository.UserRepository;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/auth")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SessionRepository sessionRepository;

  @PostMapping("/login")
  public @ResponseBody ResponseEntity<?> authUser (@Valid @RequestBody User user) {
    try {
      User authUser = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());

      if(authUser == null) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }

      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      c.add(Calendar.HOUR_OF_DAY, 5);
      Date expiresAt = c.getTime();

      // Replace by a safer token
      String token = authUser.getId();

      Session session = new Session(token, authUser.getId(), expiresAt);
      sessionRepository.save(session);

      return new ResponseEntity<>(token, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewUser (@Valid @RequestBody User user) {
    try {
      userRepository.save(user);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping
  public @ResponseBody ResponseEntity<?> updateUser (@Valid @RequestBody User user) {
    try {
      return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping
  public @ResponseBody ResponseEntity<?> deleteUser (@Valid @RequestBody User user) {
    try {
      userRepository.delete(user);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "id") String userId) {
    User user = userRepository.findOne(userId);
    if(user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(user);
  }

  @GetMapping(path="/all")
  public @ResponseBody ResponseEntity<Iterable<User>> getAllUsers() {
    return ResponseEntity.ok().body(userRepository.findAll());
  }
}