package com.attendance_backend.controller;

import com.attendance_backend.model.Students;
import com.attendance_backend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Dodanie studenta
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Students addStudent(@RequestBody Students students) {
        return studentService.addStudent(students);
    }

    // Pobranie studenta po numerze indeksu
    @GetMapping("/{indexNumber}")
    public Students getStudentByIndexNumber(@PathVariable String indexNumber) {
        return studentService.getStudentByIndexNumber(indexNumber);
    }

    // Pobranie wszystkich studentów
    @GetMapping
    public List<Students> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Usunięcie studenta
    @DeleteMapping("/{indexNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable String indexNumber) {
        studentService.deleteStudent(indexNumber);
    }

    // Zaktualizowanie studenta
    @PutMapping("/{indexNumber}")
    public Students updateStudent(@PathVariable String indexNumber, @RequestBody Students studentsDetails) {
        return studentService.updateStudent(indexNumber, studentsDetails);
    }
}
