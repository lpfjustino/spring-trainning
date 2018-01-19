package com.example.springtrainning.service;

import com.example.springtrainning.entity.Session;
import com.example.springtrainning.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Service
public class SessionService {
  @Autowired
  private SessionRepository sessionRepository;

  public Session addNewSession (Session session) {
      return sessionRepository.save(session);
  }

  public Session updateSession (Session session) {
      return sessionRepository.save(session);
  }

  public void deleteSession (Session session) {
      sessionRepository.delete(session);
  }

  public Session getSessionById( String sessionId) {
    return sessionRepository.findOne(sessionId);
  }

  public Iterable<Session> getAllSessions() {
    return sessionRepository.findAll();
  }

  public Session getUserSession(String token) {
    if(token.isEmpty())
      return null;

    Session userSession = sessionRepository.findByToken(token);

    return userSession;
  }
}