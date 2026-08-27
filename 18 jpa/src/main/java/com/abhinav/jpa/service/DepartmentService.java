package com.abhinav.jpa.service;


import com.abhinav.jpa.model.Department;
import com.abhinav.jpa.model.Student;
import com.abhinav.jpa.repository.DepartmentRepository;
import com.abhinav.jpa.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private StudentRepository studentRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository,
            StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createDepartment(Department department) {

        Student student1 = new Student();
        student1.setName("Aditys");
        student1.setDepartment(department);
        Student student2 = new Student();
        student2.setName("tara");
        student2.setDepartment(department);
        Student student3 = new Student();
        student3.setName("rakesh");
        student3.setDepartment(department);
        Student student4 = new Student();
        student4.setName("tarun");
        student4.setDepartment(department);

        department.getStudents().addAll(List.of(student1,student2,student3,student4));

        departmentRepository.save(department);

        //        studentRepository.save(student1);
//        studentRepository.save(student2);
//        studentRepository.save(student3);
//        studentRepository.save(student4);
    }

    @Transactional
    public void createDepartment(
            Department department,
            String studentName
    ) {

        departmentRepository.save(department);



    }
}