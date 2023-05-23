package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/StudentSystem";
  private static final String USER = "student";
  private static final String PASS = "pass";

  private static final String FIND_STUDENT_BY_ID_AND_PASSWORD = "SELECT * FROM StudentSystem.students where id=? and password=?";
  private static final String FIND_COURSES_FOR_STUDENT = "SELECT id, grade FROM StudentSystem.courses join \n" +
      "\tStudentSystem.grades on courses.id = grades.cID where sID=?";


  public Student findStudentByIdAndPassword(int id, String pass) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = getConnection();
      assert connection != null;
      statement = connection.prepareStatement(FIND_STUDENT_BY_ID_AND_PASSWORD);
      statement.setInt(1, id);
      statement.setString(2, pass);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Student(resultSet.getInt("id"),
            resultSet.getString("name"));
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(statement);
      close(connection);
    }
    return null;
  }

  public List<Course> findCoursesForStudent(int id) {
    Connection connection = null;
    PreparedStatement statement = null;
    List<Course> courses = new ArrayList<>();

    try {
      connection = getConnection();
      assert connection != null;
      statement = connection.prepareStatement(FIND_COURSES_FOR_STUDENT);
      statement.setInt(1, id);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Course course = new Course(resultSet.getString("id"), resultSet.getInt("grade"));
        courses.add(course);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(statement);
      close(connection);
    }
    return courses;
  }

  private Connection getConnection() {
    try {
      Class.forName(DRIVER);
      return DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void close(Connection con) {
    if (con != null) {
      try {
        con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static void close(Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
