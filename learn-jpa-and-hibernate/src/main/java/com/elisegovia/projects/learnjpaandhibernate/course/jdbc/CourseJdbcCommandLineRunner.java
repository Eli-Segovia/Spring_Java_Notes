package com.elisegovia.projects.learnjpaandhibernate.course.jdbc;

import com.elisegovia.projects.learnjpaandhibernate.course.beans.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository courseJdbcRepository;

    @Override
    public void run(String... args) throws Exception {
        courseJdbcRepository.insert(new Course(1L, "Eli Sucks", "Johnny"));
        courseJdbcRepository.insert(new Course(2L, "Eli Sucks2", "Should not Appear"));
        courseJdbcRepository.insert(new Course(3L, "Eli Sucks3", "Johnny"));
        courseJdbcRepository.insert(new Course(4L, "Eli Sucks4", "Johnny"));

        courseJdbcRepository.delete(2L);
    }
}
