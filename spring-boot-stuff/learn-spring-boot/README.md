# Simple REST API Using Spring Boot

In this project, we are building a simple REST API. I will add notes as I go here...

## What is the end goal of Spring Boot?

- Help you build **Production-Ready** apps quickly
  - Quickly:
    - Spring Initialz
    - Starter Projects
    - Auto Configuration
    - DevTools
  - Production Ready
    - Logging
    - Configuration for Different Environments
    - Monitoring

### @RestController

SpringBoot looks like it uses the MVC pattern. The @RestController annotation tells Spring that the annotated class is going to have business logic, and will expose the result of the logic at an endpoint. We  expose that endpoint with the @RequestMapping annotation, with the endpoing provided therein. 

We actually only have to do that: Spring takes care of everything. (I'm surprised actually.)