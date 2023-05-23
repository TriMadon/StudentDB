package com.example.studentdbv3.service;


import com.example.studentdbv3.entity.Course;
import com.example.studentdbv3.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
  private final CourseRepository courseRepository;

  @Autowired
  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> findCoursesForStudentById(Long id){
    return courseRepository.findCoursesByStudentId(id);
  }

  public Double getCourseAverage(String courseID){
    return courseRepository.findCourseAverage(courseID);
  }

  public Double getCourseMaximum(String courseID){
    return courseRepository.findCourseMaximum(courseID);
  }

  public Double getCourseMinimum(String courseID){
    return courseRepository.findCourseMinimum(courseID);
  }

  public Double getCourseMedian(String courseID){
    return courseRepository.findCourseMedian(courseID);
  }

}
