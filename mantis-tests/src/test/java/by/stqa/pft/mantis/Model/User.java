package by.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class User {
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "username")
  private String username;

  @Expose
  @Column(name = "realname")
  private String realname;

  @Expose
  @Column(name = "email")
  private String email;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getRealname() {
    return realname;
  }

  public String getEmail() {
    return email;
  }
}
