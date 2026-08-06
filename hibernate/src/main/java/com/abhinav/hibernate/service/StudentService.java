package com.abhinav.hibernate.service;


import com.abhinav.hibernate.model.Student;
import com.abhinav.hibernate.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

//    public List<Student> getAllStudents() {
//        return studentRepository.getStudent();
//    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(Student student , long id) {
        Student student1 = studentRepository.findById(id);
        student1.setName(student.getName());
        student1.setEmail(student.getEmail());
        student1.setAge(student.getAge());
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student1 = studentRepository.findById(id);

        if(student1 == null)
        {
            throw  new RuntimeException("Student not found");

        }

        studentRepository.delete(student1);

    }
}