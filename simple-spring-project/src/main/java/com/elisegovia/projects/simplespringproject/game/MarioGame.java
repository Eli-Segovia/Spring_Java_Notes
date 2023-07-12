package com.elisegovia.projects.simplespringproject.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// @Component means that Spring's IoC container is managing this component
// @Primary means that this bean will be the default if there are two candidates (i.e. MarioGame > SuperContra)
@Component
@Primary
public class MarioGame implements Game{
    /**
     * Simple Class for a representation of a Mario game
     * Doesn't really do anything
     */


    public void up() {
        System.out.println("Jump");
    }

    public void down() {
        System.out.println("Down Pipe");
    }

    public void left() {
        System.out.println("Stop");
    }

    public void right() {
        System.out.println("Forward");
    }
}
