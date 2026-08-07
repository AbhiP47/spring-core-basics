package com.abhinav.hibernate.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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

    @Column(precision = 5, scale = 2)
    private BigDecimal percent;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    private LocalDate dateOfBirth;

    @Transient
    private String displayName;

    @Lob
    private String profileDesc;

    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isMonitor;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "pa_street")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "pa_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "pa_state")
            )

    })
    private Address permanentAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "ca_street")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "ca_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "ca_state")
            )

    })
    private Address currentAddress;

    @ElementCollection
    @CollectionTable(name = "Student_skill",
    joinColumns = @JoinColumn(name="student_id"))
    private Set<Student> skills;

    @ElementCollection
    @CollectionTable(name = "student_address",
    joinColumns = @JoinColumn(name = "student_id"))
    private Set<Address> addresses;

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

    public BigDecimal getPercent() {
        return percent;
    }

    public Student setPercent(BigDecimal percent) {
        this.percent = percent;
        return this;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public Student setStatus(StudentStatus status) {
        this.status = status;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Student setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Student setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public Student setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
        return this;
    }

    public Boolean getMonitor() {
        return isMonitor;
    }

    public Student setMonitor(Boolean monitor) {
        isMonitor = monitor;
        return this;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public Student setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
        return this;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public Student setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
        return this;
    }

    public Set<Student> getSkills() {
        return skills;
    }

    public Student setSkills(Set<Student> skills) {
        this.skills = skills;
        return this;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Student setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Student(Long id, String name, String email, int age, BigDecimal percent, StudentStatus status, LocalDate dateOfBirth, String displayName, String profileDesc, Boolean isMonitor, Address permanentAddress, Address currentAddress, Set<Student> skills, Set<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.percent = percent;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
        this.displayName = displayName;
        this.profileDesc = profileDesc;
        this.isMonitor = isMonitor;
        this.permanentAddress = permanentAddress;
        this.currentAddress = currentAddress;
        this.skills = skills;
        this.addresses = addresses;
    }
}
