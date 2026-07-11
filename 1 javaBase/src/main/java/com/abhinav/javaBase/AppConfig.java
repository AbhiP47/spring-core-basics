package com.abhinav.javaBase;

import com.abhinav.jarDependency.CartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.abhinav.javaBase")
// if we do not provide the package name for component scan then it will by default take the current package it is in
public class AppConfig {

// CartService is a class from the custom created jar file which is added as a dependency to our project so
//		We cannot use the spring's IOC container functionality to create its object and
//		also we cannot add the @Component annotation to it since it is only a read-only file which is present in the byte code .class format.
    /*
    Here the problem is that Spring is not able to create the object and also manage it for the cart service.
    To solve this problem we can create the object by ourselves and let it be called by the Spring by using the @Bean annotation in the App config class .
    The @Bean annotation tells the spring framework while reading the app config configuration to  call the object
     which is annotated with the @Bean annotation,
     which will give you an object and store that object in the IOC container
     */

    @Bean
    public User createUser()
    {
        return new User(23,"Abhinav");
    }

    @Bean
    public CartService createCartService()
    {
        return new CartService();
    }

    /*
    Also use the @Bean annotation for creating the objects by ourselves and giving it to the IoC container to store it.
    For example, for the card payment, upi payment, or even the order service object
    We do not need to use the @Component annotation.
    Just creating the objects manually and let the spring manage them .

    We can also use the @Primary and @Qualifier annotations with the beans while using the @Bean annotation
     but the name in the qualifier has to be the name of the method instead of class name in camel case which we are using to create the bean.

     @Bean Annotation is prioritized if we have both  @Component & @Bean annotation.
     */


}
