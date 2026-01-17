package com.elisegovia.projects.webservices.soap_course_management.soap;

import com.elisegovia.courses.*;
import com.elisegovia.projects.webservices.soap_course_management.soap.beans.Course;
import com.elisegovia.projects.webservices.soap_course_management.soap.exception.CourseNotFoundException;
import com.elisegovia.projects.webservices.soap_course_management.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * There are a few annotations here that should be addressed.
 *
 * Endpoint -> tells Spring FW that the current class is going to have methods that handle requests -- specifically soap requests.
 *
 * PayloadRoot -> tells Spring Webservices what namespace (where the wsdl will be defined) and what specific method (localPart) from the xsd is going to be handled
 *                by the method it is defined in
 *
 * RequestPayload -> tells Spring that the object provided should be mapped to the actual request sent over
 *
 * ResponsePayload -> tells Spring that the object we return is the response. idk seems redundant but that's Java.
 *
 * I guess the two above are needed for the marshalling/unmarshalling between Java and XML
 *
 */

@Endpoint
public class CourseDetailsEndpoint {

    /**
     * This service is what actually handles the business logic in the endpoints below. This class is like the "controller"
     * It creates the connection between the XSD schema that defines how messages should be send, and the endpoint, and the
     * WSDL and sends requests to the correct location to be processed.
     */
    @Autowired
    private CourseDetailsService service;

    // This is the method that will control the GetCourseDetailsRequest traffic.
    // we handle the logic with CourseDetailsService.

    // input - GetCourseDetailsRequest

    // output - GetCourseDetailsResponse
    @PayloadRoot(namespace = "http://elisegovia.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest (@RequestPayload GetCourseDetailsRequest request) {
        Course course = service.getCourseById(request.getId());
        if (null == course) {
            throw new CourseNotFoundException("Course with course ID " + request.getId() + " not found");
        }
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(courseDetailsFromCourse(course));

        return response;
    }

    @PayloadRoot(namespace = "http://elisegovia.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest (@RequestPayload GetAllCourseDetailsRequest request) {

        List<Course> courses = service.getCourses();
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

        for (Course course : courses) {
            response.getCourseDetails().add(courseDetailsFromCourse(course));
        }

        return response;
    }

    @PayloadRoot(namespace = "http://elisegovia.com/courses", localPart = "DeleteCourseRequest")
    @ResponsePayload
    public DeleteCourseResponse processDeleteCourseRequest (@RequestPayload DeleteCourseRequest request) {
        int deletedCount = service.deleteById(request.getId());
        DeleteCourseResponse response = new DeleteCourseResponse();
        response.setStatus(deletedCount >= 1 ? Status.SUCCESS : Status.FAILURE);
        return response;
    }


    private CourseDetails courseDetailsFromCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setDescription(course.getDescription());
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        return courseDetails;
    }


}
