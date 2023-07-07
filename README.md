# Spring_Java_Notes
Notes and mini project for Spring/Spring Boot for Java


## Spring/Spring Boot Terminology Cheat Sheet

| Term               | Definition            |
| ------------------ | --------------------- |
| Spring Bean        | A class that is managed by Spring. It must include the @Component annotation for Spring to look at it |
| Dependency         | (in Java) A class that our current class depends on in order to work. (I.E a Car Class might depend on a Brake Class ) |
| Dependency Injection | Providing the Spring Bean the other Spring Beans that it needs instead of The Spring Bean in Question making them itself (I.E. The Car will get the Brake and does not have to explicitly make it)|
| Inversion of Control | What Dependency Injection achieves: essentially, that we don't have to worry about creating the necessary objects, but rather, we just let Spring do it with some annotations telling it what we probably want |
| Component Scan     | Which package Spring looks to settle Autowired Components |
| IoC Container      | Manages the lifecycle of beans and dependencies |
| Autowiring         | Process of wiring dependencies for a Spring Bean |

## Spring/Spring Boot Annotations Cheat Sheet (Not in Any particular order)

| Annoation            | What it does       |
| -------------------- | ------------------ |
| @Component           | Tells Spring that this is a class it should manage. This is now a Spring Bean |
| @SpringBootApplication | (might need Correction) Essentially the container of the main method: where the application actually does its work |
| @ComponentScan | Tells Spring which package to looks into (including its sub-packages) -- defaults to the current package it is on |
| @Autowired           | Basically tells Spring that we want to get the value of the Autowired stuff (usually the properties of a Constructor) from Beans that Spring is managing  (I.E. we want to get the Brake that Spring is managing as the Brake of the Car |
| @Primary             | A way to handle ambiguity if two or more types of beans can be autowired into a Spring Bean. For example, we might have DiskBrake or we might have RimBrake that can be autowired into Bicycle. We can set the @Primary to help Spring handle the case and choose the Primary Bean only |\
