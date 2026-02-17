package com.elisegovia.projects.learnjpaandhibernate.course.jpa;

import com.elisegovia.projects.learnjpaandhibernate.course.beans.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public void delete(Long id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    public Course select(Long id) {
        return entityManager.find(Course.class, id);
    }

}
