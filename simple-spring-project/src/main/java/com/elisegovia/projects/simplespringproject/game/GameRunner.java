package com.elisegovia.projects.simplespringproject.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component Tells Spring's IoC container to manage this component and its dependencies
@Component
public class GameRunner {


    /**
     * About Autowiring:
     * There are three types of autowiring:
     * - Constructor-based = Dependency vals set by creating the Bean using the constructor (the preferred way) just set
     * the annotation above the constructor or even above the field (Spring is smart enough to figure out constructor)
     *
     * - Setter-based = Dependency vals set by using the setter method. Just set the annotation above the setter method
     *
     * - Field-based = No setter or constructor is used: just insert the val directly
     */
    // @Autowired => This marks this field as a dependency that we don't have to manage
    // Spring will be smart: it will look at its IoC container, find a game, and it will
    // put that game right here. If there are more than two bean candidates that can be autowired
    // we have to add some more annotations: look at Look at the MarioGame to see this in action
    @Autowired
    private Game game;

    public GameRunner(Game game) {
        this.game = game;
    }

    public void run() {
        // Simply run through games methods :)
        game.up();
        game.down();
        game.left();
        game.right();
    }




}
