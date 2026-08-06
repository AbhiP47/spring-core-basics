package com.abhinav.hibernate.repository;

import com.abhinav.hibernate.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public void save(Student student)
    {
        entityManager.persist(student);
    }

    public Student findById(Long id)
    {
        return entityManager.find(Student.class, id);
    }

    public void update(Student student)
    {
        entityManager.merge(student);
    }

    public void delete(Student student)
    {
        entityManager.remove(student);
    }
}
