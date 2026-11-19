# Student Management System

A Spring Boot RESTful API application for managing student records, academic performance analysis, and skill tracking, integrated with MySQL and Spring Data JPA.

## Features

- **CRUD Operations**: Create, read, update, and delete student profiles.
- **Academic Performance Analysis**:
  - Calculate average scores across programming and mathematics.
  - Determine student performance categories and stronger subjects.
  - Generate merit rankings.
  - Identify students needing programming improvement.
- **Advanced Filtering & Grouping**:
  - Search students by email address or enrolled course.
  - Group students sharing identical technical skill sets.
- **Exception Handling**: Global exception handling with structured error responses.

---

## Tech Stack

- **Java**: 17
- **Framework**: Spring Boot 4.x (Web MVC, Spring Data JPA, Validation)
- **Database**: MySQL (via XAMPP / local MySQL instance)
- **Build Tool**: Maven

---

## Getting Started

### Prerequisites

- Java 17 or later installed
- MySQL Server (e.g., via XAMPP) running on port `3306`
- Maven (or use the included `./mvnw` wrapper)

### Database Setup

1. Start MySQL server in XAMPP.
2. Create a database named `student_management`:
   ```sql
   CREATE DATABASE student_management;
   ```
3. Configure credentials in [src/main/resources/application.properties](file:///c:/Users/avant/OneDrive/Desktop/studentmanagement/src/main/resources/application.properties) if needed:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/student_management
   spring.datasource.username=root
   spring.datasource.password=
   ```

### Running the Application

Using the Maven wrapper:

```bash
# On Windows
.\mvnw.cmd spring-boot:run

# On Linux/macOS
./mvnw spring-boot:run
```

The application will run on port `8083`: `http://localhost:8083`

---

## API Endpoints

### Student Management

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/students` | Add a new student |
| `GET` | `/api/students` | Retrieve all students |
| `GET` | `/api/students/{id}` | Retrieve student by ID |
| `PUT` | `/api/students/{id}` | Update student details |
| `DELETE` | `/api/students/{id}` | Remove a student |

### Analytics & Queries

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/students/{id}/analysis` | Get student analysis (average, category, stronger subject) |
| `GET` | `/api/students/merit-list` | Get list of students ranked by merit |
| `GET` | `/api/students/search/email?email={email}` | Search students by email |
| `GET` | `/api/students/search/course?course={course}` | Search students by course |
| `GET` | `/api/students/programming-improvement` | Get students needing programming improvement |
| `GET` | `/api/students/same-skills` | Group students with identical skills |

---

## Sample Request Body (`POST /api/students`)

```json
{
  "studentId": 101,
  "studentName": "John Doe",
  "email": "john.doe@example.com",
  "courseName": "Computer Science",
  "programmingScore": 85.5,
  "mathematicsScore": 92.0,
  "technicalSkills": ["Java", "Spring Boot", "MySQL"]
}
```
