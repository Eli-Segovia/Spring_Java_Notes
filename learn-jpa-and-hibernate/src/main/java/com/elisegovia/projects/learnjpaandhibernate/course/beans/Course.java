package com.elisegovia.projects.learnjpaandhibernate.course.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name="Course") // Not needed because class name matches with DB table. Show as example
public class Course {

    @Id // Indicates this is the primary key of the Table
    private Long id;

    @Column(name="name") // not needed because Java member var is the same as the DB field. show as example
    private String name;

    @Column(name="author") // not needed because Java member var is the same as the DB field. show as example
    private String author;

    public Course() {

    }

    public Course(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
