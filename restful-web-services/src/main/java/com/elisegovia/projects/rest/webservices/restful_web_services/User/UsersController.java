package com.elisegovia.projects.rest.webservices.restful_web_services.User;

import com.elisegovia.projects.rest.webservices.restful_web_services.User.Beans.User;
import com.elisegovia.projects.rest.webservices.restful_web_services.User.Error.Exceptions.UserNotFoundException;
import jakarta.validation.Valid;
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
        User user = userDao.get(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " is not found");
        }
        return user;
    }

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

    // Get /users/{id}
    // Gets specific user
    @DeleteMapping("/users/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userDao.delete(id);
    }
}
