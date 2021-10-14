package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.*;

@Entity //Annotation to map Student class to database for Hibernate.
@Table //Annotation to declare the class as table for the database.
public class Student {
    @Id //Auto-generate values in column Id for variable id. Part of DB configuration.
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    
    //Annotation means variable age doesn't need to become a table column.
    //It will be calculated at runtime.
    @Transient
    private Integer age;

    public Student() {
    }

    public Student(Long id, 
                   String name, 
                   String email, 
                   LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, 
                   String email, 
                   LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [age=" + age + 
                      ", dob=" + dob + 
                      ", email=" + email + 
                      ", id=" + id + 
                      ", name=" + name + 
                      "]";
    }

}
