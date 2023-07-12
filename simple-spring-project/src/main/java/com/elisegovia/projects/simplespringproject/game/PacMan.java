package com.elisegovia.projects.simplespringproject.game;

import org.springframework.stereotype.Component;

@Component
public class PacMan implements Game{
    /**
     * Simple Class for a representation of a PacMan game
     * Doesn't really do anything
     */


    public void up() {
        System.out.println("Eat Dot Up");
    }

    public void down() {
        System.out.println("Eat Dot Down");
    }

    public void left() {
        System.out.println("Eat Dot Left");
    }

    public void right() {
        System.out.println("Eat Dot Right");
    }
}
