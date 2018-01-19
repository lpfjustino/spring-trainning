package com.example.springtrainning.controller;

import com.example.springtrainning.service.SessionService;
import com.example.springtrainning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springtrainning.entity.Session;
import com.example.springtrainning.entity.User;
import com.example.springtrainning.service.SessionService;
import com.example.springtrainning.service.UserService;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(path="/auth")
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private SessionService sessionService;

  @PostMapping("/login")
  public @ResponseBody ResponseEntity<?> authUser (@Valid @RequestBody User user) {
    try {
      Session userSession = userService.authUser(user);

      if(userSession == null) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }

      return new ResponseEntity<>(userSession.getToken(), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public @ResponseBody ResponseEntity<?> addNewUser (@Valid @RequestBody User user) {
    try {
      userService.addNewUser(user);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping
  public @ResponseBody ResponseEntity<?> updateUser (@Valid @RequestBody User user) {
    try {
      return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping
  public @ResponseBody ResponseEntity<?> deleteUser (@Valid @RequestBody User user) {
    try {
      userService.deleteUser(user);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "id") String userId) {
    User user = userService.getUserById(userId);
    if(user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(user);
  }

  @GetMapping(path="/all")
  public @ResponseBody ResponseEntity<Iterable<User>> getAllUsers() {
    return ResponseEntity.ok().body(userService.getAllUsers());
  }
}