package com.example.springtrainning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springtrainning.entity.Session;
import com.example.springtrainning.service.SessionService;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/session")
public class SessionController {
  @Autowired
  private SessionService sessionService;

  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewSession (@Valid @RequestBody Session session) {
    try {
      Session newSession = sessionService.addNewSession(session);
      return new ResponseEntity<>(newSession, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping
  public @ResponseBody ResponseEntity<?> updateSession (@Valid @RequestBody Session session) {
    try {
      Session updatedSession = sessionService.updateSession(session);
      return new ResponseEntity<>(updatedSession, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping
  public @ResponseBody ResponseEntity<?> deleteSession (@Valid @RequestBody Session session) {
    try {
      sessionService.deleteSession(session);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Session> getSessionById(@PathVariable(value = "id") String sessionId) {
    Session session = sessionService.getSessionById(sessionId);
    if(session == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(session);
  }

  @GetMapping(path="/all")
  public @ResponseBody ResponseEntity<Iterable<Session>> getAllSessions() {
    return ResponseEntity.ok().body(sessionService.getAllSessions());
  }
}