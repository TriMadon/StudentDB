package com.example.studentdbv3.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "students")
public final class Student implements Serializable {
  @Id
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Basic(optional = false)
  @Column(nullable = false)
  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Student(Long id, String password) {
    this.id = id;
    this.password = password;
  }

  public Student() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}