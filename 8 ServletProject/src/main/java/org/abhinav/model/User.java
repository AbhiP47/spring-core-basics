package org.abhinav.model;

public class User {

    private  Integer id;
    private String name;
    private String email;
    private  String mobile;

    public User(Integer id, String mobile, String name, String email) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public User setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
