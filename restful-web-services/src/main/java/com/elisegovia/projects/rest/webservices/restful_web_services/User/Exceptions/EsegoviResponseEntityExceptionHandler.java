package com.elisegovia.projects.rest.webservices.restful_web_services.User.Exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

/**
 * Here we are extending the way Spring MVC handles exceptions -> we are then adding an exception handler
 * that will return a ResponseEntity with some custom Details using our ErrorDetails class
 *
 * The Custom Details will be returned as JSON -> cool.
 *
 * Some annotations to note: @ExceptionHandler -> tells spring to route to this method upon error(???)
 *                           @ControllerAdvice -> tells spring that controllers within the project should use this exception handler
 */
@ControllerAdvice
public class EsegoviResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleUserException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails details = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails details = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(details, HttpStatus.NOT_FOUND);
    }

}
