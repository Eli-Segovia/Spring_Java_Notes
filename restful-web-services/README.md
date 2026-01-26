# Restful APIs

In this project we are going to be doing stuff with RESTful apis.

## What's happening in the background

### How are requests handled?

__DispatcherServlet__ → mapped to the root url "/". Will end up routing the traffic to the right controller and
Java Business logic. Dispatcher servlet is configured automatically with Spring Default shit.


### How are Java POJOs/Beans returned as JSON?

__@ResponseBody and JacksonHttpMessageConverters__

When we configure a class to be a RestController, the @ResponseBody is expected as the output of our methods.
So, spring knows that the returns of all the methods should be converted to JSON. What actually does the converting
is Jackson which is a tool that can work with XML and JSON.

### Using Path Params
```java
    @GetMapping(path = "/some/path/{name}")
    someGetMethod(@PathVariable String name) {
        return wtvJson(name);
    }
```
So. Looks like you just set the path param as {var} and then you specify that you want to use it by using the `PathVariable`
annotation.


### Designing REST Api Bull

![img.png](img.png)

These be the Methods we will be implementing :)


### Make POJO out of resources, e.g.: User and Post and Daos

Make User POJO. This is just a Java representation of the Database Resource. To actually interact with the Database
we use a DAO -> Data Access Object. Naming convention is <Resource>Dao or <Resource>DaoService i.e. `UserDao` or `UserDaoService`
