package org.abhinav;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.abhinav")
public class AppConfig {

    @Bean(initMethod = "start")
    public CartService createCart()
    {
        return new CartService();
    }
}
/*

An Initialization Callback is any custom lifecycle method you provide to Spring that the container automatically
executes immediately after a bean’s dependencies have been fully populated, but before the bean is put into active service.

It acts as a standardized "setup hook" where you can safely run initialization logic—like opening database
 connections, warming up caches, or starting background threads—knowing that every single @Autowired or @Value
 field is fully populated and will not be null.

In Spring, you can implement an initialization callback in three distinct ways:

Annotation-based: Annotating a custom method with @PostConstruct (the most common and recommended approach).

Programmatic: Implementing the InitializingBean interface and overriding its afterPropertiesSet() method.

Declarative Configuration: Specifying an explicit initMethod attribute inside your Java configuration file
(e.g., @Bean(initMethod = "setup")).

Think of it as your personal hook to run setup logic after Spring has injected all dependencies into your bean,
but before the bean is allowed to handle any real work.

There are three ways to define an init method in Spring. Here is exactly how they work, what is inside them,
 and how they execute.

1. The Three Ways to Define an Init Method
Way A: The @PostConstruct Annotation (Modern & Standard)
This is the most common approach in modern Spring Boot applications. You simply place @PostConstruct on top of
any void, no-argument method.

Way B: The initMethod Attribute in @Bean (For Third-Party Classes)
If you are using a class from an external library, you can't open its source code
to add @PostConstruct. Instead, you declare it in your @Configuration file using the initMethod property:

Way C: The InitializingBean Interface (Legacy)
This is an older, formal way of doing things. Your class implements InitializingBean and overrides the afterPropertiesSet() method:
 */