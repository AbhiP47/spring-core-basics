package com.abhinav.aop.service;

import com.abhinav.aop.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public Student createStudent(Student student) {
        System.out.println("Student saved");

//        throw new RuntimeException("Some error Occured");

        return student;
    }
}
