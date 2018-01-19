package com.example.springtrainning.service;

import com.example.springtrainning.entity.Session;
import com.example.springtrainning.entity.User;
import com.example.springtrainning.repository.SessionRepository;
import com.example.springtrainning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SessionRepository sessionRepository;

  public Session authUser (User user) throws Exception {
      User authUser = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());

      if(authUser == null) {
        throw new Exception();
      }

      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      c.add(Calendar.HOUR_OF_DAY, 5);
//      c.add(Calendar.SECOND, 10);
      Date expiresAt = c.getTime();

      // Replace by a safer token
      String token = authUser.getId();

      sessionRepository.deleteByUserId(authUser.getId());

      Session session = new Session(token, authUser.getId(), expiresAt);
      return sessionRepository.save(session);
  }

  public User addNewUser (User user) {
      return userRepository.save(user);
  }

  public User updateUser (User user) {
      return userRepository.save(user);
  }

  public void deleteUser (User user) {
      userRepository.delete(user);
  }

  public User getUserById( String userId) {
    return userRepository.findOne(userId);
  }

  public Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}