package com.example.studentdbv3.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "courses")
public final class Course implements Serializable {

  @Id
  @Column(nullable = false)
  private String id;

  private Double grade;

  public Course(String id, Double grade) {
    this.id = id;
    this.grade = grade;
  }

  public Course(String id) {
    this.id = id;
  }

  public Course() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Double getGrade() {
    return grade;
  }

  public void setGrade(Double grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id='" + id + '\'' +
        ", grade=" + grade +
        '}';
  }
}

