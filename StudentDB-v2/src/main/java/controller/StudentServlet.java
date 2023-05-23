package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Course;
import model.Student;
import model.StudentDAO;

import java.beans.JavaBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/student")
public class StudentServlet extends HttpServlet {
  private StudentDAO studentDAO = new StudentDAO();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  public String showTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    StringBuilder builder = new StringBuilder();
    Student student = (Student)  request.getSession().getAttribute("currentStudent");
    List<Course> courses = studentDAO.findCoursesForStudent(student.getId());
    for (Course course : courses) {
      builder.append("<tr>\n" +
          "    <th><span> <a href=\"course-page.jsp?courseID="+ course.id() +"\">" + course.id() + "</a><span></th>\n" +
          "           <td>" + course.studentGrade() + "</td>\n" +
          "          </tr>");
    }
    System.out.println(builder.toString());
    return builder.toString();
  }


}
