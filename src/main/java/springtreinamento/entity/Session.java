package springtreinamento.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Session {

  public Session(String token, String id) {
    this.setToken(token);
    this.setId(id);
  }

  public Session(String token, String id, Date expiresAt) {
    this.setToken(token);
    this.setId(id);
    this.setExpiresAt(expiresAt);
  }

  public Session() {}

  private String id;

  @Id
  private String token;

  private Date expiresAt;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Date expiresAt) {
    this.expiresAt = expiresAt;
  }
}
