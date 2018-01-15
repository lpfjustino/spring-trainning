package springtreinamento.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  @NotBlank
  private String name;

  @NotBlank
  private String country;

  public Person(){}

  public Person(String id, String name, String country){
    this.setId(id);
    this.setName(name);
    this.setCountry(country);
  }

  public Person(String name, String country){
    this.setName(name);
    this.setCountry(country);
  }

  public Person(String id){
    this.setId(id);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return String.format(
        "Person[id=%d, name='%s', country='%s']",
        id, name, country);
  }
}
