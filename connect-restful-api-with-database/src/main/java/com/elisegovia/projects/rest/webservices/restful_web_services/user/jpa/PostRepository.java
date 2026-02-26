package com.elisegovia.projects.rest.webservices.restful_web_services.user.jpa;

import com.elisegovia.projects.rest.webservices.restful_web_services.user.beans.Post;
import com.elisegovia.projects.rest.webservices.restful_web_services.user.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * This is what we use to talk to the real DB. We inherit the JpaRepository to get all the
 * DB methods for free
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
}
