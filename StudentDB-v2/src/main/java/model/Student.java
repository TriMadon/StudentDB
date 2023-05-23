package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public final class Student implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  private final int id;
  private final String name;

  public Student(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    Student that = (Student) obj;
    return this.id == that.id &&
        Objects.equals(this.name, that.name);
  }

  @Override
  public String toString() {
    return "Student[" +
        "getId=" + id + ", " +
        "name=" + name + ']';
  }

  public int getId() {
    return id;
  }

  public String name() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }


}