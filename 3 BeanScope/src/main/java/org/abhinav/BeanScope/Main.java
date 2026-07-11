package org.abhinav.BeanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
        If the scope of the order service is set to prototype, And we are not using the bean anywhere in our code.
         Spring will not create the order service bean.
        We can see this as we set the scope to prototype and do not use the bean order service created constructor, which will not be called.
        This is because the prototype uses the lazy initialization technique for bean creation.
        Lazy Initialization defers the creation of a bean until the exact moment it is explicitly requested
        for the first time—either by calling context.getBean() or because another bean needs it injected as
        a dependency.

        Remember if we create an object by ourselves by using the new keyword or by using the @Bean annotation in AppConfig,
         we can create multiple beans in the IOC container and the IOC container will manage all the beans.
         */

        OrderService orderService = context.getBean(OrderService.class);

        OrderService orderService1 = context.getBean(OrderService.class);


        /*
        In Spring, if you do not specify a scope for a bean, it defaults to Singleton scope.
        A standard Java Singleton means there is exactly one instance per ClassLoader.

        When you call context.getBean(OrderService.class) the first time, Spring checks its internal registry
        (a cache of singleton objects). Since it doesn't exist yet, it instantiates it, stores it in the cache,
         and returns it to orderService.

        When you call context.getBean(OrderService.class) the second time for orderService1,
        Spring looks at the cache, sees it already built an instance, and simply returns a reference to that
         exact same object in memory.

        Since both variables point to the same memory address, the == comparison evaluates to true.

         */
        System.out.println(orderService == orderService1);
    }
}
/*
Choosing between Singleton and Prototype beans depends entirely on whether your class needs to hold unique state for a specific task or user.

When to use a Singleton Bean (Default)
You should use a Singleton bean when your class is stateless or holds read-only, shared configuration.
 This means the bean performs actions but doesn't store data that changes from one request to another.

Core Use Cases: * Service layers (e.g., OrderService, UserService) containing pure business logic.

Data Access Objects (DAOs) and Repositories (e.g., UserRepository).

Controllers (e.g., @RestController entry points).

Why: Creating only one instance saves massive amounts of memory and reduces CPU overhead because
Spring doesn't have to keep rebuilding the object.

When to use a Prototype Bean
You should use a Prototype bean when your class is stateful—meaning it holds mutable, unique user data
or operational data that cannot be safely shared across different threads.

Core Use Cases:

Multi-step data processors or multi-threaded tasks where each worker needs its own independent settings.

Shopping carts or session-specific user buffers where data belongs strictly to one client interaction.

File upload or report generation exports that track unique progress states (percentComplete, currentLine).

Why: If multiple users tried to access a stateful object running as a Singleton, they would overwrite each
other’s data, causing major concurrency bugs and data leaks.

Rule of Thumb for Your Code Architecture
Ask yourself: "If two different users execute a method on this bean at the exact same millisecond, will they
corrupt each other's data?" * If No, make it a Singleton.

If Yes (because it updates class-level instance fields), make it a Prototype.
 */