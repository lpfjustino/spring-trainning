package springtreinamento.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public User() {}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  @NotBlank
  private String login;

  @NotBlank
  private String password;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return this.login + " / " + this.password;
  }
}
