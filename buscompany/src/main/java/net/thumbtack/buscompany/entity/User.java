package net.thumbtack.buscompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "surname")
  private String surname;
  @Column(name = "name")
  private String name;
  @Column(name = "middlename")
  private String middlename;
  @Column(name = "login", unique = true)
  private String login;
  @Column(name = "password")
  private String password;
  @Column(name = "type")
  private UserType userType;

  public User(String surname, String name, String middlename, String login, String password,
      UserType userType) {
    this(0, surname, name, middlename, login, password, userType);
  }


}
