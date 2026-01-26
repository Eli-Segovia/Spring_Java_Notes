package com.elisegovia.projects.rest.webservices.restful_web_services.helloworld;

import com.elisegovia.projects.rest.webservices.restful_web_services.helloworld.beans.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {


//    @RequestMapping(method = RequestMethod.GET, path="/hello-world")
//    public String getHelloWorld() {
//        return "Hello World";
//    }

    // What is shown above is the equivalent of below :)

    @GetMapping(path="/hello-world")
    public String getHelloWorld() {
        return "Hello bitch";
    }

    // Return a bean that gets jsonified
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean getHelloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // example using Path Parameters
    @GetMapping(path="/hello-world/path/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }


}
