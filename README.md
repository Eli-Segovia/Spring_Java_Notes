# Spring_Java_Notes
Notes and mini project for Spring/Spring Boot for Java

## Bootstrapping a Spring project

There is a lot of set up and configuration that comes a long with geting a Spring project ready to go. I personally have worked with React a bit, so I think a good analogy is all the setup that you need to get React good to go (dependencies, webpack, eslint, babel, etc.). We use CreateReactApp to bootstrap React applications. In a similar fashion, there is spring initializr. It is a webpage at [start.spring.io](https://start.spring.io). I am not sure if there is a CLI for it, but the webpage is simple enough to bootstrap an application. For the most part, all the example projects in this repo use spring initialzr 

## Terminology

| Term               | Definition            |
| ------------------ | --------------------- |
| Spring Bean        | A class that is managed by Spring. It must include the @Component annotation for Spring to look at it |
| Dependency         | (in Java) A class that our current class depends on in order to work. (I.E a Car Class might depend on a Brake Class ) |
| Dependency Injection | Providing the Spring Bean the other Spring Beans that it needs instead of The Spring Bean in Question making them itself (I.E. The Car will get the Brake and does not have to explicitly make it)|
| Inversion of Control | What Dependency Injection achieves: essentially, that we don't have to worry about creating the necessary objects, but rather, we just let Spring do it with some annotations telling it what we probably want |
| Component Scan     | The way Spring finds dependencies: Which package Spring looks to settle Autowired Components |
| IoC Container      | Manages the lifecycle of beans and dependencies |
| Autowiring         | Process of wiring dependencies for a Spring Bean |
| ApplicationContext | One of the ways Spring handles beans and dependencies - this is the preferred way of doing it, and the simple-spring-project in this repo uses ApplicationContext as an example|
| BeanFactory        | The other way Spring handles beans and dependencies |

## Annotations

| Annoation            | What it does       |
| -------------------- | ------------------ |
| @Component           | Tells Spring that this is a class it should manage. This is now a Spring Bean |
| @SpringBootApplication | (might need Correction) Essentially the container of the main method: where the application actually does its work |
| @ComponentScan | Tells Spring which package to looks into (including its sub-packages) -- defaults to the current package it is on |
| @Autowired           | Basically tells Spring that we want to get the value of the Autowired stuff (usually the properties of a Constructor) from Beans that Spring is managing  (I.E. we want to get the Brake that Spring is managing as the Brake of the Car |
| @Primary             | A way to handle ambiguity if two or more types of beans can be autowired into a Spring Bean. For example, we might have DiskBrake or we might have RimBrake that can be autowired into Bicycle. We can set the @Primary to help Spring handle the case and choose the Primary Bean only |\

## Spring Modules

Spring Framework is divided into modules:
* Core: IoC Container, etc.
* Testing: Mock Objects, Spring MVC Test, etc.
* Data Access: Transactions, JDBC, JPA
* Web Servlet: Spring MVC
* Web Reactive: Spring WebFlux
* Integration: JMS

We can choose the modules that we need to use for our Application


## Spring Projects

Spring Projects are like additional pieces to Spring. These extensions add extra functionality/ features that we can leverage in the project.

Example of Spring Projects:
* Spring Boot (The most popular) - Used to build Microservices
* Spring Cloud - Used to build cloud native applications
* Spring Data - For databases NoSQL and Relational
* Spring Integration - Addresses challenges with integration with other apps
* Spring Security - Secure application and API or microservice