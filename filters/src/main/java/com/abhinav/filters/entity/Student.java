package com.abhinav.filters.entity;

public class Student {
    private int email;
    private int name;

    public int getName() {
        return name;
    }

    public Student setName(int name) {
        this.name = name;
        return this;
    }

    public int getEmail() {
        return email;
    }

    public Student setEmail(int email) {
        this.email = email;
        return this;
    }

    public Student(int name, int email) {
        this.name = name;
        this.email = email;
    }
}
