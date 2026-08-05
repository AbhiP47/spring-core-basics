package com.abhinav.aop.repository;



import com.abhinav.aop.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public void save(Student student) {
        System.out.println("Student saved");

    }
}