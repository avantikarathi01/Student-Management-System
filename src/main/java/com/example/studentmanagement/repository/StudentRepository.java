
package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Search student by email
    List<Student> findByEmail(String email);

    // Search students by course
    List<Student> findByCourseName(String courseName);

}
