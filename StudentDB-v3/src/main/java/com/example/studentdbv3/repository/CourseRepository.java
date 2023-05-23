package com.example.studentdbv3.repository;

import com.example.studentdbv3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
  @Query(value = "SELECT id, grade FROM StudentSystem.courses join StudentSystem.grades on courses.id = grades.cID where sID=?" ,
      nativeQuery = true)
  List<Course> findCoursesByStudentId(@Param("sID") Long studentId);

  @Query(value = "SELECT avg(grade) FROM StudentSystem.grades where cID=?",
      nativeQuery = true)
  Double findCourseAverage(@Param("cID") String courseID);

  @Query(value = "SELECT max(grade) FROM StudentSystem.grades where cID=?",
      nativeQuery = true)
  Double findCourseMaximum(@Param("cID") String courseID);

  @Query(value = "SELECT min(grade) FROM StudentSystem.grades where cID=?",
      nativeQuery = true)
  Double findCourseMinimum(@Param("cID") String courseID);

  @Query(value = """
      WITH ranked AS (
        SELECT grade,\s
          ROW_NUMBER() OVER (ORDER BY grade) AS r,
          count(grade) OVER () AS c\s
        FROM grades WHERE cID=?
      ),
      median AS (
        SELECT grade\s
        FROM ranked\s
        WHERE r IN (floor((c+1)/2), ceil((c+1)/2))
      )
      SELECT AVG(grade) FROM median""",
      nativeQuery = true)
  Double findCourseMedian(@Param("cID") String courseID);
}
