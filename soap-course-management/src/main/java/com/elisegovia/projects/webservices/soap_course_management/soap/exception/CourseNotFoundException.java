package com.elisegovia.projects.webservices.soap_course_management.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * When this error is thrown, the Soap Fault will say that this is a client error: the request
 * is requesting a value that does not exist
 */
@SoapFault(faultCode = FaultCode.CLIENT)
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}



/**
 *  We can also define a custom Fault Code. That would look something like:
 *
 *  @SoapFault(faultCode = FaultCode.CUSTOM,
 *                         customFaultCode="{http://elisegovia.com/courses}001_COURSE_NOT_FOUND")
 *
 * public class CourseNotFoundException extends RuntimeException {
 *
 *     public CourseNotFoundException(String message) {
 *         super(message);
 *     }
 * }
 */

/**
 * Error/Exception handling is another concept in and of itself. You can deal with it how you like
 */
