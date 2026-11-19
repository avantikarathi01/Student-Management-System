
package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @GetMapping("/merit-list")
    public List<Student> getMeritList() {
        return studentService.getMeritList();
    }

    @GetMapping("/search/email")
    public List<Student> searchByEmail(@RequestParam String email) {
        return studentService.searchByEmail(email);
    }

    @GetMapping("/search/course")
    public List<Student> searchByCourse(@RequestParam String course) {
        return studentService.searchByCourse(course);
    }

    @GetMapping("/programming-improvement")
    public List<Student> getStudentsNeedingProgrammingImprovement() {
        return studentService.getStudentsNeedingProgrammingImprovement();
    }

    @GetMapping("/same-skills")
    public Map<Set<String>, List<Student>> getStudentsWithSameSkills() {
        return studentService.getStudentsWithSameSkills();
    }
    @GetMapping("/{id}/analysis")
public Map<String, Object> getStudentAnalysis(@PathVariable Long id) {

    Student student = studentService.getStudentById(id);

    Map<String, Object> result = new HashMap<>();

    result.put("average", studentService.calculateAverage(student));
    result.put("category", studentService.getPerformanceCategory(student));
    result.put("strongerSubject", studentService.getStrongerSubject(student));

    return result;
}
}
