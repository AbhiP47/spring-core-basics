package com.abhinav.aop.service;

import com.abhinav.aop.model.Student;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public Student createStudent(Student student) {
        System.out.println("Student saved");

//        throw new RuntimeException("Some error Occured");

        return student;
    }

    public @Nullable String dummyMethod(String s) {
        return s;
    }
}
