package springtreinamento.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Session {

  public Session(String token, String userId, Date expiresAt) {
    this.setToken(token);
    this.setUserId(userId);
    this.setExpiresAt(expiresAt);
  }

  public Session() {}

  private String userId;

  @Id
  private String token;

  private Date expiresAt;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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
