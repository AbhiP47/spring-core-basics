package com.abhinav.springBootFilters.controller;


import com.abhinav.springBootFilters.dto.StudentResponseDto;
import com.abhinav.springBootFilters.entity.Student;
import com.abhinav.springBootFilters.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setName(student.getName());
        responseDto.setMessage("Student Successfully Saved");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}