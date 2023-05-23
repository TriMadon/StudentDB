package model;

import java.util.Objects;


public final class Course {
  private final String id;
  private final int studentGrade;

  public Course(String id, int studentGrade) {
    this.id = id;
    this.studentGrade = studentGrade;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    Course that = (Course) obj;
    return Objects.equals(this.id, that.id) &&
        this.studentGrade == that.studentGrade;
  }

  @Override
  public String toString() {
    return "Course[" +
        "getId=" + id + ", " +
        "studentGrade=" + studentGrade + ']';
  }

  public String id() {
    return id;
  }

  public int studentGrade() {
    return studentGrade;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, studentGrade);
  }


}
