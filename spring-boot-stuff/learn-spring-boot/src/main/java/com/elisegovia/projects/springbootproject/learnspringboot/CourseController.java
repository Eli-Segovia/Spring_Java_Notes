package com.elisegovia.projects.springbootproject.learnspringboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * The big picture...
 * We want to make a simple REST API at endpoint /courses, and we want to
 * return it in the format:
 * /courses
 * Course: id, name, author
 * [
 * {
 *     "id" : 1,
 *     "name": "Learn AWS",
 *     "author": "eliSegovia"
 * }
 * ]
 */

/*
    Tells Spring boot that this is going to be accepting requests and going to return appropriate data
 */
@RestController
public class CourseController {

    // /courses
    // Course: id, name, author
    /*
        Tells Spring boot that this method is going to be mapped to the getCourses() method -- essentially we are
        exposing the endpoint "/courses"
     */
    @RequestMapping("/courses")
    public List<Course> getCourses() {
        return Arrays.asList(
            new Course(1, "Learn AWS", "Eli Segovia"),
            new Course(2, "Learn Java", "Eli Segovia"),
            new Course(3, "Learn C++", "Eli Segovia"),
            new Course(4, "Learn Death", "Eli u"),
            new Course(5, "Learn Dragon", "Eli uuuuu;")
        );
    }

}
