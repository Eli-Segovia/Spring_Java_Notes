package com.elisegovia.projects.rest.webservices.restful_web_services.helloworld.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UsersController {

    private final UserDaoService userDao;

    public UsersController(UserDaoService userDao) {
        this.userDao = userDao;
    }

    // Get /users
    // Gets all users
    @GetMapping("/users")
    public List<User> get() {
        return userDao.get();
    }

    // Get /users/{id}
    // Gets specific user
    @GetMapping("/users/{id}")
    public User get(@PathVariable Integer id) {
        return userDao.get(id);
    }

    // Post /users
    // Posts a user
    // RequestBody maps the request body to the POJO we expect. here we are expecting a user json to map to our
    // user POJO
    @PostMapping("/users")
    public ResponseEntity<User> post(@RequestBody User user) {
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
}
