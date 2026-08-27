package com.abhinav.jpa.service;

import com.abhinav.jpa.model.Department;
import com.abhinav.jpa.model.Student;
import com.abhinav.jpa.repository.DepartmentRepository;
import com.abhinav.jpa.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void createStudent(Student student, Long deptId) {
        Department department =
                departmentRepository.getDepartmentById(deptId);

        student.setDepartment(department);
        department.getStudents().add(student);

        studentRepository.save(student);
    }

    @Transactional
    public void createStudent(Student student, String deptName) {
        Department department = new Department();
        department.setName(deptName);

        student.setDepartment(department);
        department.getStudents().add(student);

        departmentRepository.save(department);
        studentRepository.save(student);
    }

}