package com.elisegovia.projects.rest.webservices.restful_web_services.user;

import com.elisegovia.projects.rest.webservices.restful_web_services.user.beans.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/*
 * This is what we use to talk to the fake DB. We represent all of our REST methods. (i.e.)
 * getAll (get all Users)
 * get ( get a single user)
 * post (Save a user)
 *
 */
@Component
public class UserDaoService {

    /**
     * Before we implemented the DB stuff, the following code was used to represent a fake DB
     * If it's not commented out that means I am still using it for experimentation purposes :)
     */
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(24)));
        users.add(new User(2, "Bella", LocalDate.now().minusYears(31)));
        users.add(new User(3, "Chris", LocalDate.now().minusYears(27)));
        users.add(new User(4, "Diana", LocalDate.now().minusYears(35)));
        users.add(new User(5, "Ethan", LocalDate.now().minusYears(22)));
        users.add(new User(6, "Fiona", LocalDate.now().minusYears(29)));
        users.add(new User(7, "George", LocalDate.now().minusYears(41)));
        users.add(new User(8, "Hannah", LocalDate.now().minusYears(26)));
        users.add(new User(9, "Isaac", LocalDate.now().minusYears(33)));
        users.add(new User(10, "Julia", LocalDate.now().minusYears(28)));
    }

    public List<User> get() {
        return users;
    }

    public User get(Integer id) {
        for (User user: users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }

    public User post(User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    public Boolean delete(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        User user = users.stream().filter(predicate).findFirst().orElse(null);
        if (null != user) {
            users.remove(user);
            return true;
        }
        return false;
    }


}
