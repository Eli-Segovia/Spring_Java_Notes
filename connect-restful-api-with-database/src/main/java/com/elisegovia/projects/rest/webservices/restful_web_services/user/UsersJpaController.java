package com.elisegovia.projects.rest.webservices.restful_web_services.user;

import com.elisegovia.projects.rest.webservices.restful_web_services.user.beans.User;
import com.elisegovia.projects.rest.webservices.restful_web_services.user.error.Exceptions.UserNotFoundException;
import com.elisegovia.projects.rest.webservices.restful_web_services.user.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsersJpaController {

//    private final UserDaoService userDao; no longer using this fake

    private final UserRepository repository;

    public UsersJpaController(UserRepository repository) {
        this.repository = repository;
    }

    // Get /users
    // Gets all users
    @GetMapping("/jpa/users")
    public List<User> get() {
        return repository.findAll();
    }

    // Get /users/{id}
    // Gets specific user
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> get(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("user with id " + id + " is not found");
        }
        EntityModel<User> model = EntityModel.of(user.get());
        WebMvcLinkBuilder allUsersLink = linkTo(methodOn(this.getClass()).get()); // notice that `.get` is the poorly named method that gets all users.
        model.add(allUsersLink.withRel("all-users"));
        return model;
    }

    // Post /users
    // Posts a user
    // RequestBody maps the request body to the POJO we expect. here we are expecting a user json to map to our
    // user POJO
    @PostMapping("/jpa/users")
    public ResponseEntity<User> post(@Valid @RequestBody User user) {
        // dao adds new user
        User newUser = repository.save(user);

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
    @DeleteMapping("/jpa/users/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
