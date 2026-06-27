package com.abhinav.javaBase;

import org.springframework.stereotype.Component;

/*

You cannot use @Component here because Spring does not know how to automatically instantiate this class.
 When you annotate a class with @Component, you are telling Spring: "Hey, take control of this class, instantiate it automatically, and manage it as a bean."
However, Spring faces a major road-block with your current implementation:

1. Because you defined this custom constructor, Java suppresses the default hidden no-args constructor.
When Spring tries to initialize your application context, it scans the @Component annotation, looks for a way to build it,
 and panics because it doesn't know what values to pass for int age and String name. This will throw a BeanCreationException at runtime.

 2. Beyond the constructor crash, there is a design reason why this is an anti-pattern.

In a typical Spring application, classes generally fall into two categories:

Spring Beans (Services/Components): These are stateless, functional singletons (like a UserService, DatabaseRepository, or PaymentGateway).
You want one instance of these to live throughout the application to do heavy lifting.

Domain Models / POJOs (Data Carriers): These represent unique dynamic data (like a specific User entity, a Product, or an Invoice).

Your User class is a data carrier. It holds a specific user's name and age. If Spring manages it as a default @Component (which is a singleton),
 you would only ever have one global User bean shared across your entire application.
  If User A logs in and changes the name to "Abhinav", User B will suddenly see their name changed to "Abhinav" too.

To solve this problem we can create the object by ourselves and let it be called by the Spring by using the @Bean annotation in the App config class .
    The @Bean annotation tells the spring framework while reading the app config configuration to  call the object
     which is annotated with the @Bean annotation,
     which will give you an object and store that object in the IOC container
 */
//@Component


public class User {
    private String name;
    private int age;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }
}
