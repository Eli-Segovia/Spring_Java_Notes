package com.elisegovia.projects.webservices.soap_course_management.soap.service;

import com.elisegovia.projects.webservices.soap_course_management.soap.beans.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;


/**
 * Here we are going to put the logic to actually do shit in the java
 * Whereas the endpoint is what actually grabs the request and is what we use to
 * interface with the API, we define the java business logic below and tell
 * spring to manage it. Then we inject this logic into the endpoint :)
 */
@Component
public class CourseDetailsService {

    // fake database of courses.
    private static List<Course> courses = new ArrayList<>();

    static {
        // fake db vals;
        Course c1 = new Course(1, "Spring", "Whatever");
        Course c2 = new Course(2, "Java", "Whatever");
        Course c3 = new Course(3, "Python", "Whatever");
        Course c4 = new Course(4, "Bruce", "Whatever");

        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
    }

    // get course by ID
    public Course getCourseById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    // get all courses
    public List<Course> getCourses() {
        return courses;
    }

    // delete course
    public int deleteById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                courses.remove(c);
                return 1;
            }
        }
        return 0;
    }
    // update course and add new course
}
