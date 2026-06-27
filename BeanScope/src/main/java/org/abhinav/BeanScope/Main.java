package org.abhinav.BeanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
        
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
