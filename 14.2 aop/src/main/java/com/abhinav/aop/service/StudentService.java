package com.abhinav.aop.service;


import com.abhinav.aop.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public Student createStudent(Student student) {
        System.out.println("Student saved");

//        throw new RuntimeException("Some error Occured");

        return student;
    }

    public String getStudent()
    {
        String s = "All Student data";
        System.out.println(s);
        return s;
    }
}
