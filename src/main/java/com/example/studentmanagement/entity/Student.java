
package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Long studentId;

    private String studentName;

    private String email;

    private String courseName;

    private double programmingScore;

    private double mathematicsScore;

    @ElementCollection
    @CollectionTable(
        name = "student_skills",
        joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "skill")
    private List<String> technicalSkills = new ArrayList<>();

    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor
    public Student(Long studentId, String studentName,
                   String email, String courseName,
                   double programmingScore,
                   double mathematicsScore,
                   List<String> technicalSkills) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.courseName = courseName;
        this.programmingScore = programmingScore;
        this.mathematicsScore = mathematicsScore;
        this.technicalSkills = technicalSkills == null
                ? new ArrayList<>()
                : new ArrayList<>(technicalSkills);
    }

    // Getters and Setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(double programmingScore) {
        this.programmingScore = programmingScore;
    }

    public double getMathematicsScore() {
        return mathematicsScore;
    }

    public void setMathematicsScore(double mathematicsScore) {
        this.mathematicsScore = mathematicsScore;
    }

    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills == null
                ? new ArrayList<>()
                : new ArrayList<>(technicalSkills);
    }

    // toString Method
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", email='" + email + '\'' +
                ", courseName='" + courseName + '\'' +
                ", programmingScore=" + programmingScore +
                ", mathematicsScore=" + mathematicsScore +
                ", technicalSkills=" + technicalSkills +
                '}';
    }

    // equals Method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Student)) return false;

        Student other = (Student) obj;

        return java.util.Objects.equals(
                this.studentId, other.studentId
        );
    }

    // hashCode Method
    @Override
    public int hashCode() {
        return java.util.Objects.hash(studentId);
    }
}
