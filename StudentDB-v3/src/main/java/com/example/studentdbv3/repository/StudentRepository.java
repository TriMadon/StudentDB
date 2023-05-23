package com.example.studentdbv3.repository;

import com.example.studentdbv3.entity.Course;
import com.example.studentdbv3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
