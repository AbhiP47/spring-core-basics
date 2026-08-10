package com.abhinav.hibernate.model;

import jakarta.persistence.*;

    @Entity
    @Table(name="students")
    public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "student_name")
        private String name;

        @Column(nullable = false,unique = true)
        private String email;

        private int age;

        public Long getId() {
            return id;
        }

        public Student setId(Long id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Student setName(String name) {
            this.name = name;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Student setEmail(String email) {
            this.email = email;
            return this;
        }

        public int getAge() {
            return age;
        }

        public Student setAge(int age) {
            this.age = age;
            return this;
        }

        public Student(Long id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }
    }
