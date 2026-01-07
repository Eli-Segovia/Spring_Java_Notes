package com.elisegovia.projects.simplespringproject;


import com.elisegovia.projects.simplespringproject.game.Game;
import com.elisegovia.projects.simplespringproject.game.GameRunner;
import com.elisegovia.projects.simplespringproject.game.MarioGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SimpleSpringProjectApplication {

	public static void main(String[] args) {

		// spring manages components through ApplicationContext
		// we can grab that context by doing the following
		ConfigurableApplicationContext context = SpringApplication.run(SimpleSpringProjectApplication.class, args);

		// a Bean is a component that Spring manages. It is just a class. We need to specify which class and append the
		// .class to get a java.lang.Class representation of the class
		GameRunner runner = context.getBean(GameRunner.class);

		// We now run our api, and Spring will do some magic!
		runner.run();

		System.out.println("Hello there");

	}

}
