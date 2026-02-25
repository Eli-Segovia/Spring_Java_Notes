package com.elisegovia.projects.rest.webservices.restful_web_services.user.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity (name="user_details") // cannot be "user" because it is a key word in some Databases
public class User {

    protected User() {

    }

    @Id
    @GeneratedValue // The @GeneratedValue annotation in Hibernate (part of the JPA specification) is primarily used in conjunction with the @Id annotation to specify a strategy for automatically generating values for the primary key of an entity.
    private Integer id;

    @Column(name="name")
    @Size(min=2, message = "Name must be at least 2 characters long")
    private String name;

    @Column(name="birthDate")
    @Past(message = "Birthdate cannot be in the future")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user") // Maps this field to the "user" field of class Post using one-to-many relationship
    @JsonIgnore // ignores this when returning Json
    private List<Post> posts;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
