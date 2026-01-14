package com.elisegovia.projects.webservices.soap_course_management.soap;

import com.elisegovia.courses.CourseDetails;
import com.elisegovia.courses.GetCourseDetailsRequest;
import com.elisegovia.courses.GetCourseDetailsResponse;
import com.elisegovia.projects.webservices.soap_course_management.soap.beans.Course;
import com.elisegovia.projects.webservices.soap_course_management.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 *
 * There are a few annotations here that should be addressed.
 *
 * Endpoint -> tells Spring FW that the current class is going to have methods that handle requests
 * PayloadRoot -> tells Spring Webservices what namespace and what specific method (localPart) is going to hit the endpoint method
 *
 * RequestPayload -> tells Spring that the object provided should be mapped to the actual request sent over
 * ResponsePayload -> tells Spring that the object we return is the response. idk seems redundant but that's Java.
 * I guess the two above are needed for the marshalling/unmarshalling between Java and XML
 *
 */

@Endpoint
public class CourseDetailsEndpoint {

    /**
     * This is what actually handles the business logic in the endpoints below.
     */
    @Autowired
    private CourseDetailsService service;

    // This is the method that will process the GetCourseDetailsRequest
    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    @PayloadRoot(namespace = "http://elisegovia.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest (@RequestPayload GetCourseDetailsRequest getCourseDetailsRequest) {
        Course course = service.getCourseById(getCourseDetailsRequest.getId());

        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setDescription(course.getDescription());
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        response.setCourseDetails(courseDetails);
        return response;
    }


}
