package com.elisegovia.projects.learnjpaandhibernate.course.springdatajpa;

import com.elisegovia.projects.learnjpaandhibernate.course.beans.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
}
