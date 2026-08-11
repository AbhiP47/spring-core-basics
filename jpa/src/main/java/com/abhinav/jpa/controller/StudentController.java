package com.abhinav.jpa.controller;

import com.abhinav.jpa.model.Student;
import com.abhinav.jpa.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(
            @RequestBody Student student,
            @RequestParam Long deptId) {

        studentService.createStudent(student, deptId);
        return ResponseEntity.ok("DONE");
    }

    @PostMapping("/withDepartment")
    public ResponseEntity<String> createStudent(
            @RequestBody Student student,
            @RequestParam String deptName) {

        studentService.createStudent(student, deptName);
        return ResponseEntity.ok("DONE");
    }



}