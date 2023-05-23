package com.example.studentdbv3.controller;


import com.example.studentdbv3.entity.Student;
import com.example.studentdbv3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("login")
public class LoginController {

  private final HttpSession httpSession;
  private final StudentService studentService;

  @Autowired
  public LoginController(StudentService studentService, HttpSession httpSession) {
    this.studentService = studentService;
    this.httpSession = httpSession;
  }

  @RequestMapping(path = "auth")
  public String getStudent(@RequestParam Long studentID, @RequestParam String password, Model model) {
    Optional<Student> optional = studentService.findStudentById(studentID);
    if (httpSession.getAttribute("student") != null)
      return redirectToStudentView(optional.get());
    if (optional.isEmpty()) {
      return loadFailedLogin(model);
    }
    Student student = optional.get();
    if (student.getPassword().equals(password))
      return redirectToStudentView(student);
    else return loadFailedLogin(model);
  }

  @RequestMapping
  public String getLoginPage(){
    return "login";
  }

  public String loadFailedLogin(Model model){
    String failMessage = "Log in Failed! try again";
    model.addAttribute("loginFailed", failMessage);
    return "login";
  }

  public String redirectToStudentView(Student student){
    httpSession.setAttribute("student", student);
    return "redirect:/student";
  }
}
