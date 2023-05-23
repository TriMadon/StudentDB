package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Course;
import model.CourseDAO;
import model.Student;
import model.StudentDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
  private CourseDAO courseDAO = new CourseDAO();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  public String showTable(HttpServletRequest request, HttpServletResponse response) {
    String courseID = request.getParameter("courseID");
    System.out.println(courseID);

    Double avg = courseDAO.findAverageOfCourse(courseID);
    Double max = courseDAO.findMaxOfCourse(courseID);
    Double min = courseDAO.findMinOfCourse(courseID);
    Double median = courseDAO.findMedianOfCourse(courseID);

    return "<tr>\n" +
        "                <th> Average </th>\n" +
        "                <td>" + avg + "</td>\n" +
        "          </tr>" +
        "<tr>\n" +
        "                <th> Maximum </th>\n" +
        "                <td>" + max + "</td>\n" +
        "          </tr>" +
        "<tr>\n" +
        "                <th> Minimum </th>\n" +
        "                <td>" + min + "</td>\n" +
        "          </tr>" +
        "<tr>\n" +
        "                <th> Median </th>\n" +
        "                <td>" + median + "</td>\n" +
        "          </tr>";
  }
}
