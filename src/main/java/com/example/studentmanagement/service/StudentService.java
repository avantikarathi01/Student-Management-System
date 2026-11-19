
package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. Add Student + Duplicate ID Validation
    public Student addStudent(Student student) {

        if (student.getStudentId() == null) {
            throw new RuntimeException("Student ID is required!");
        }

        if (studentRepository.existsById(student.getStudentId())) {
            throw new RuntimeException("Student ID already exists!");
        }

        validateScores(student);

        return studentRepository.save(student);
    }

    // 2. Get All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 3. Search Student By ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Student not found!"));
    }

    // 4. Update Student Scores and Skills
    public Student updateStudent(Long id, Student updatedStudent) {

        Student student = getStudentById(id);

        validateScores(updatedStudent);

        student.setProgrammingScore(
                updatedStudent.getProgrammingScore());

        student.setMathematicsScore(
                updatedStudent.getMathematicsScore());

        student.setTechnicalSkills(
                updatedStudent.getTechnicalSkills());

        return studentRepository.save(student);
    }

    // 5. Delete Student
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    // 6. Validate Scores (0 to 100)
    private void validateScores(Student student) {

        double programming = student.getProgrammingScore();
        double mathematics = student.getMathematicsScore();

        if (!Double.isFinite(programming) ||
            !Double.isFinite(mathematics) ||
            programming < 0 || programming > 100 ||
            mathematics < 0 || mathematics > 100) {

            throw new RuntimeException(
                    "Scores must be between 0 and 100!");
        }
    }

    // 7. Calculate Average
    public double calculateAverage(Student student) {
        return (student.getProgrammingScore()
                + student.getMathematicsScore()) / 2.0;
    }

    // 8. Performance Category
    public String getPerformanceCategory(Student student) {

        double average = calculateAverage(student);

        if (average >= 85) {
            return "Excellent";
        } else if (average >= 70) {
            return "Good";
        } else if (average >= 50) {
            return "Satisfactory";
        } else {
            return "Needs Improvement";
        }
    }

    // 9. Stronger Subject
    public String getStrongerSubject(Student student) {

        if (student.getProgrammingScore() >
            student.getMathematicsScore()) {

            return "Programming";

        } else if (student.getMathematicsScore() >
                   student.getProgrammingScore()) {

            return "Mathematics";

        } else {
            return "Both are Equal";
        }
    }

    // 10. Merit List (Descending Average)
    public List<Student> getMeritList() {

        List<Student> students = studentRepository.findAll();

        students.sort((s1, s2) ->
            Double.compare(
                calculateAverage(s2),
                calculateAverage(s1)
            )
        );

        return students;
    }

    // 11. Search By Email
    public List<Student> searchByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // 12. Search By Course
    public List<Student> searchByCourse(String course) {
        return studentRepository.findByCourseName(course);
    }

    // 13. Students Needing Programming Improvement
    public List<Student> getStudentsNeedingProgrammingImprovement() {

        List<Student> result = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {

            if (student.getProgrammingScore() < 50 &&
                student.getMathematicsScore() >= 75) {

                result.add(student);
            }
        }

        return result;
    }

    // 14. Students Having Same Technical Skills
    public Map<Set<String>, List<Student>> getStudentsWithSameSkills() {

        Map<Set<String>, List<Student>> skillGroups = new HashMap<>();

        for (Student student : studentRepository.findAll()) {

            Set<String> skills =
                    new TreeSet<>(student.getTechnicalSkills());

            skillGroups.computeIfAbsent(
                    skills, k -> new ArrayList<>()
            ).add(student);
        }

        // Keep only groups containing at least two students
        skillGroups.entrySet().removeIf(
                entry -> entry.getValue().size() < 2
        );

        return skillGroups;
    }
}
