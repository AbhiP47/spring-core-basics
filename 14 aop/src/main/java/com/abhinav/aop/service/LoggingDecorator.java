package com.abhinav.aop.service;

import com.abhinav.aop.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggingDecorator implements StudentService{

    @Autowired
    private StudentServiceImpl studentService;

    @Override
    public void createStudent(Student student) {
        LoggingServiceUtil.logStart("Student","Create student");
        studentService.createStudent(student);
        LoggingServiceUtil.logEnd("Student","Create student");
    }
}
