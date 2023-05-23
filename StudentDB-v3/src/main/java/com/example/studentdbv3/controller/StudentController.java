package com.example.studentdbv3.controller;

import com.example.studentdbv3.entity.Course;
import com.example.studentdbv3.entity.Student;
import com.example.studentdbv3.service.CourseService;
import com.example.studentdbv3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
  private final StudentService studentService;
  private final CourseService courseService;
  private final HttpSession httpSession;

  @Autowired
  public StudentController(StudentService studentService, CourseService courseService, HttpSession httpSession) {
    this.studentService = studentService;
    this.courseService = courseService;
    this.httpSession = httpSession;
  }

  @GetMapping(path = "getAll")
  public List<Student> getAllStudents(){
    return studentService.findAllStudents();
  }

  @RequestMapping(path = "/")
  public String mainRedirect(HttpSession session, Model model){
    if (session.isNew() || session.getAttribute("student") == null)
      return "redirect:/login";
    return getStudentPage(model);
  }

  @RequestMapping(path = "/student")
  public String getStudentPage(Model model){
    Student student = (Student) httpSession.getAttribute("student");
    model.addAttribute("studentWelcome", student.getName());
    List<Course> courses = courseService.findCoursesForStudentById(student.getId());
    model.addAttribute("courses", courses);
    return "studentPage";
  }


}
