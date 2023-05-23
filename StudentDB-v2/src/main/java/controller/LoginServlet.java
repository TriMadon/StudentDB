package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Student;
import model.StudentDAO;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private StudentDAO studentDAO = new StudentDAO();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = Integer.parseInt(request.getParameter("id"));
      String pass = request.getParameter("pass");
      Student student = studentDAO.findStudentByIdAndPassword(id, pass);
      System.out.println(id);
      System.out.println(pass);

      RequestDispatcher dispatcher;

      if (student != null) {
        HttpSession session = request.getSession(true);
        session.setAttribute("currentStudent", student);
        dispatcher = request.getRequestDispatcher("student-page.jsp");
      } else {
        dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
      }
      dispatcher.forward(request, response);

    } catch (NumberFormatException | IOException e) {
      System.out.println(e.getMessage());
    }
  }



}
