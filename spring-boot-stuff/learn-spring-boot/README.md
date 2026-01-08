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
  
## Spring Boot Starter Projects

Spring boot starter projects provide convenient dependency descriptors for different features.

For example the Tomcat server that is used for this practice uses the starter project that takes care of all that configuration and just works right out of the box.

### @RestController

SpringBoot looks like it uses the MVC pattern. The @RestController annotation tells Spring that the annotated class is going to have business logic, and will expose the result of the logic at an endpoint. We  expose that endpoint with the @RequestMapping annotation, with the endpoing provided therein. 

We actually only have to do that: Spring takes care of everything. (I'm surprised actually.) We don't even have to include it in the main method. It just knows that the rest controller is going to expose the provided routes on port 8080 since it is already automatically deploying a tomcat server.


## Auto Configuration

Configuration for the application is automatically used by the application. What types of configuration it uses is based on:
* Which Frameworks are in the class path
* what the existing configuration is (i.e. Annotations)


With springboot a lot of things for the web uses some predefined crap. This probably comes from the starter project config. So a lot of stuff points to a file called `application.properties`. We can set package log level there. Probably some other crap too.

## Profiles

Profiles allow you to have different configurations for the different environments. E.g dev,test,production,etc:
* Different Databases
* Different Web Services
* Different Configuration files

## Complex Configuration Properties

Here is the pattern:

Make a java class and an associated properties file. Something like

#### The Class (look at the actual file for more context)

```java
package blah;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "currency-service")
@Component
public class CurrencyServiceConfiguration {
    private String url;
    private String username;
    private String key;
    
    // getters and setter whatever
}
```
#### The properties file
```properties
currency-service.url=someBull
currency-service.username=someMoreBull
currency-service.key=theMostBull
```

The Class now represents this properties file and we can use those properties wherever we may need them in our code.


## Embedded Servers

2 paradigms:

1. Deploy Wars onto a java server
   * Install Java
   * Install Web/App Server
   * Deploy War
  
2. Used Embedded Server
   * Install Java
   * Run JAR (Server is already a part of the jar)
  
Server Examples:
* Tomcat
* Jetty
* Undertow

