package com.example.studentdbv3.config;


import com.example.studentdbv3.entity.Student;
import com.example.studentdbv3.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.List;

@Configuration
public class StudentConfig {

//  @Bean
//  CommandLineRunner commandLineRunner (
//      StudentRepository repository) {
//    return args -> {
//      Student mohammad = new Student(1L, "Mohammad", "1111");
//      Student faris = new Student(2L, "Faris", "2222");
//      Student bob = new Student(3L, "Bob", "3333");
//      Student john = new Student(4L, "John", "4444");
//      Student tasnim = new Student(5L, "Tasnim", "5555");
//      Student yara = new Student(6L, "Yara", "6666");
//      repository.saveAll(List.of(mohammad, faris, bob, john, tasnim, yara));
//    };
//  }
}


