package com.elisegovia.projects.learnjpaandhibernate.course.jdbc;

import com.elisegovia.projects.learnjpaandhibernate.course.beans.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY =
            """
                insert into course(id, name, author)
                values(?,?,?)
            """;

    private static String DELETE_QUERY =
            """
            DELETE FROM COURSE WHERE id = ?
            """;

    private static String SELECT_QUERY =
            """
            SELECT * FROM COURSE WHERE id = ?
            """;

    public void insert(Course course) {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void delete(Long id) {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course select(Long id) {
        return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<Course>(Course.class), id);
    }
}
