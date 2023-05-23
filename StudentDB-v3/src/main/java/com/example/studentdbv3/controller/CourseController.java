package com.example.studentdbv3.controller;

import com.example.studentdbv3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(path = "/courseAPI")
public class CourseController {

  private final CourseService courseService;
  private final HttpSession httpSession;

  @Autowired
  public CourseController(CourseService courseService, HttpSession httpSession) {
    this.courseService = courseService;
    this.httpSession = httpSession;
  }

  @RequestMapping("/{courseID}")
  public String listCourseGrades(@PathVariable String courseID, Model model){
    Double avg = courseService.getCourseAverage(courseID);
    Double max = courseService.getCourseMaximum(courseID);
    Double min = courseService.getCourseMinimum(courseID);
    Double med = courseService.getCourseMedian(courseID);
    model.addAttribute("courseID", courseID);
    model.addAttribute("avg", avg);
    model.addAttribute("max", max);
    model.addAttribute("min", min);
    model.addAttribute("med", med);
    return "coursePage";
  }
}
