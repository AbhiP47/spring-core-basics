package org.abhinav.BeanScope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
Singleton scope is the default bean scope in the Spring Framework, where the IoC container
creates and manages exactly one instance of a bean per container lifecycle.
When a bean is configured as a singleton, Spring initializes it during application startup
(unless marked as lazy) and stores it in an internal registry cache. Any subsequent requests
for that bean—whether via dependency injection into other components or explicit calls
to context.getBean()—will always receive a reference to that exact same shared instance in memory.
This stateless approach is highly efficient for core business infrastructure, such as service layers,
 repositories, and controllers, as it minimizes object creation overhead and ensures consistency
 across the application.

Prototype scope, conversely, forces the Spring container to create a brand-new instance of the bean
every single time it is requested or injected into another component. Unlike singletons, Spring does
 not maintain a persistent record or cache of prototype instances once they are initialized and wired;
  the container configures the bean, hands it off to the client code, and relinquishes responsibility
  for its lifecycle management. This makes prototype scope ideal for stateful objects, dynamic data
  carriers, or threads that maintain unique, non-shareable run-time data, though developers must manage
  memory reclamation manually as Spring will not invoke @PreDestroy hooks for prototype beans.
 */


/*
Eager Initialization means that Spring creates, configures, and caches your bean immediately during the
application startup phase. By default, all Singleton beans are eagerly initialized. When you run new
 AnnotationConfigApplicationContext(), Spring scans your classes and instantiates these beans right
 then and there, before your application even processes its first request. The primary advantage is
 that any configuration errors, missing dependencies, or database connectivity issues are caught
  immediately at startup. However, it increases the initial application boot time and consumes memory
   for beans that might not be used right away.

Lazy Initialization defers the creation of a bean until the exact moment it is explicitly requested
for the first time—either by calling context.getBean() or because another bean needs it injected as
a dependency. You can enable this by adding the @Lazy annotation to a singleton bean. While this
significantly speeds up application startup time and conserves memory by only creating objects on-demand,
 it shifts the performance cost of object creation to the runtime user. Furthermore, if there are any hidden
 configuration bugs or deployment issues with a lazy bean, they will remain undetected until someone tries
  to use that specific feature in production.

(Note: Prototype scope beans are implicitly lazy by nature; Spring never instantiates them at startup
 because it only creates them when explicitly requested.)
 */
@Component
@Scope("prototype")
public class OrderService {

    public OrderService()
    {
        System.out.println("Order service Created");
    }
    public void placeOrder()
    {
        System.out.println("Order Placed");
    }
}
