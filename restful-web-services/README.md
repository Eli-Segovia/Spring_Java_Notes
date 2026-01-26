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

### Response Codes.

I know this but I will still make note of this:

![img_1.png](img_1.png)

So to use the right response codes in our messages:

There is this concept of a `ResponseEntity` 

this is just a class that is already implemented by Spring
It is sort of the container for a Response. Instead of returning our object directly i.e. (`public User post()...`)
we would instead do `public ResponseEntity<User> post() ...` we can then apend more metadata to our response including
the response code.

for a `201 created` it looks something like:

```java

public ResponseEntity<User> post(@RequestBody User user) {
    service.post(user);
    return ResponseEntity.created(null).build();
}
```

You can add additional metadata within the `created` method (or whichever ResponseEntity method you choose). For example
you can specify the new URI for the user created i.e. `http://localhost:8080/users/{newlyCreatedID}` as part of the metadata.
Look at the code to see that.

#### Exceptions, 400 codes, and 500 codes