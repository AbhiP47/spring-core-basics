package org.abhinav;

import org.springframework.stereotype.Component;

@Component
public class DatabseService {

    public void destroy()
    {
        System.out.println("Releasing resources  pre destroying of database bean");
    }
}

/*
A Destruction Callback is a lifecycle hook that the Spring container automatically executes right before a
 bean is destroyed and removed from memory. It acts as a standardized "cleanup hook," ensuring that your
 application shuts down gracefully without leaving lingering system leaks.Its primary role is to release
 external infrastructure resources. You use it to close database connection pools, terminate running background
 threads, shut down active network sockets, or flush file streams to the disk.

 In Spring, you can implement a destruction callback in three ways:

Annotation-based: Annotating a custom method with @PreDestroy (the standard and most common approach).

Programmatic: Implementing the DisposableBean interface and overriding its destroy() method.

Declarative Configuration: Specifying an explicit destroyMethod attribute inside your Java configuration file
 (e.g., @Bean(destroyMethod = "cleanup")).



 Two Critical Interview Pitfalls to Know:
The Prototype Exception: Spring never invokes destruction callbacks on Prototype-scoped beans. Once a
prototype bean is initialized and handed over to your application code, Spring loses track of it, leaving
resource cleanup entirely up to the standard Java Garbage Collector.

The Shutdown Trigger: Destruction callbacks only execute if the JVM shuts down gracefully
 (e.g., stopping a Spring Boot app cleanly). If the application process is killed abruptly with a harsh OS
  command (like kill -9), these hooks will be bypassed completely.
 */