package com.example.studentdbv3.service;

import com.example.studentdbv3.entity.Student;
import com.example.studentdbv3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Optional<Student> findStudentById(Long id) {
    return studentRepository.findById(id);
  }

  public List<Student> findAllStudents(){
    return studentRepository.findAll();
  }

}
