package com.abhinav.aop.controller;

import com.abhinav.aop.dto.Student;
import com.abhinav.aop.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student s = studentService.createStudent(student);
        return ResponseEntity.ok(s);
    }

    @GetMapping
    public ResponseEntity<String> getStudent() {
        return ResponseEntity.ok(studentService.getStudent());
    }
}