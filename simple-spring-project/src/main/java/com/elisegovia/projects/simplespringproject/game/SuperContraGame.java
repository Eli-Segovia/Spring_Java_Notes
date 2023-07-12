package com.elisegovia.projects.simplespringproject.game;

import org.springframework.stereotype.Component;

@Component
public class SuperContraGame implements Game{
    /**
     * Simple Class for a representation of a SuperContra game
     * Doesn't really do anything
     */


    public void up() {
        System.out.println("Up");
    }

    public void down() {
        System.out.println("Down");
    }

    public void left() {
        System.out.println("Left");
    }

    public void right() {
        System.out.println("Right");
    }
}
