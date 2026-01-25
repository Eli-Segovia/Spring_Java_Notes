package com.elisegovia.projects.rest.webservices.restful_web_services.helloworld.beans;


// Demo purpose only. when we return a POJO or Bean in a REST response, it gets returned as JSON
// when marshalled/serialized.
public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
