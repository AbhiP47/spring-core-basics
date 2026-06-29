package org.abhinav;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/*
In Spring Core, you had to act as the "coordinator" manually wiring up everything. In Spring Boot, the framework
 takes over the heavy lifting, primarily driven by a single annotation: @SpringBootApplication.

 The magic of this annotation is that it is actually a 3-in-1 combo annotation. If you peek inside its source
 code, it wraps three core Spring Core annotations into one:

A. @Configuration
It inherits the exact same @Configuration mechanics you already know. This means your main Spring Boot class is an
 AppConfig file itself. You can still write @Bean factory methods directly inside it if you want to.

B. @ComponentScan
In Spring Core, you had to explicitly type @ComponentScan("com.abhinav"). @SpringBootApplication implicitly applies
@ComponentScan on the package it is currently sitting in, plus all of its sub-packages.

Why it's better: As long as you keep your controllers, services, and repositories inside sub-packages of
 your main class, you never have to type out component scanning packages again.

C. @EnableAutoConfiguration
This is the heart of Spring Boot. In Spring Core, if you wanted to connect to a PostgreSQL database or use
a JSON parser, you had to manually download the jars, write a @Bean for the DataSource, construct the
connection pool manager, and handle configurations yourself.

@EnableAutoConfiguration uses a concept called "Class-path scanning":

It looks at your pom.xml dependencies (like a Spring Boot Starter).

It looks at what classes are available on your classpath.

It makes an assumption: "Hey, I see the PostgreSQL driver jar on the classpath, and I see a
 database URL in application.properties. The developer clearly wants a database connection. I will
  automatically build and configure the DataSource bean for them behind the scenes!"
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class,args);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.placeOrder();


    }
    @Bean
    public UserService createUserBean()
    {
        return new UserService();
    }
}