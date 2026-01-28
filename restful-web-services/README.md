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

#### Exceptions, 400 codes, and 500 codes - Exception Handling

All Spring MVC-based exceptions → that is if anything fails between request and response with our service, goes through
something called `ResponseEntityExceptionHandler` This thing returns formatted error details. One of the methods in this
class is called `handleException` and we can overwrite this method in our code to do some more customized exception handling


### Data Validation

In order to allow valid data, we need to do validation. Apparently Spring has its own starter project for validation:
`spring-boot-starter-validation` -> we bring that dependency into our pom.xml

We can then use the @Valid annotation in the following way:

```java
 // Post /users
    // Posts a user
    // RequestBody maps the request body to the POJO we expect. here we are expecting a user json to map to our
    // user POJO
    @PostMapping("/users")
    public ResponseEntity<User> post(@Valid @RequestBody User user) {
        // dao adds new user
        User newUser = userDao.post(user);

        // Get URI to new user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        // return created 201 code along with new location of new user
        return ResponseEntity
                .created(location)
                .body(newUser);
    }
```

Then we define validation within our POJO (in this case user) and the validation is automatically invoked!

For example, here is `User` with validation added:

```java

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {
    private Integer id;

    @Size(min=2)
    private String name;

    @Past
    private LocalDate birthDate;
/**
 * The rest of the code is omitted since it is not necessary
 */

```