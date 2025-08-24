package com.FinTrack.RestFull.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User {

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false, length = 50, updatable = true)
  private String name;

  @Column(name = "email", nullable = false, length = 30, updatable = true, unique = true)
  private String email;

  @Column(name = "password", nullable = false, length = 12, updatable = true)
  private String password;
}
