//This example demonstrates the four basic operations (Create, Read, Update, Delete) using JPA with MySQL as the database. 
//Hibernate is used as the JPA provider.
//1. Entity Class – Student.java

package com.example.jpa;
import javax.persistence.*;
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "student_name")
    private String name;
    @Column(name = "email")
    private String email;
    public Student() {}
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
