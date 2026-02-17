package com.elisegovia.projects.learnjpaandhibernate.course;

import com.elisegovia.projects.learnjpaandhibernate.course.beans.Course;
import com.elisegovia.projects.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.elisegovia.projects.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    // Used if we want to use the JDBCRepository where we implemented the queries ourselves.
    // @Autowired
    // private CourseJdbcRepository repository;

    // used if we want to use the JPARepository where we use JPA implementations
    @Autowired
    private CourseJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1L, "Eli Sucks", "Johnny"));
        repository.insert(new Course(2L, "Eli Sucks2", "Should not Appear"));
        repository.insert(new Course(3L, "Eli Sucks3", "Johnny"));
        repository.insert(new Course(4L, "Eli Sucks4", "Johnny"));

        repository.delete(2L);

        System.out.println(repository.select(4L));
    }
}
