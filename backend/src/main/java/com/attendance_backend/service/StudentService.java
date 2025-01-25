package com.attendance_backend.service;

import com.attendance_backend.model.Students;
import com.attendance_backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Dodanie studenta
    public Students addStudent(Students students) {
        return studentRepository.save(students);
    }

    // Pobranie studenta po numerze indeksu
    public Students getStudentByIndexNumber(String indexNumber) {
        Optional<Students> student = studentRepository.findById(indexNumber);
        return student.orElse(null);
    }

    // Pobranie wszystkich studentów
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    // Usunięcie studenta
    public void deleteStudent(String indexNumber) {
        studentRepository.deleteById(indexNumber);
    }

    // Zaktualizowanie studenta
    public Students updateStudent(String indexNumber, Students studentsDetails) {
        Students students = studentRepository.findById(indexNumber).orElse(null);
        if (students != null) {
            students.setStudentName(studentsDetails.getStudentName());
            students.setStudentSurname(studentsDetails.getStudentSurname());
            students.setStudentsGroup(studentsDetails.getStudentsGroup());
            return studentRepository.save(students);
        }
        return null;
    }
}
