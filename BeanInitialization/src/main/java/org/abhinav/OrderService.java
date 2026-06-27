package org.abhinav;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/*
THe by default scope of any class is singleton and the default
 initialization is eager but by using the @Lazy annotation we can make the initialization lazy.
 Which means that the objects or the beans will be created only when it is injected or being used.

 Note : We can not change the initialization of the prototype beans from lazy to eager. It is not possible in Spring.
 */
@Component
//@Lazy
public class OrderService {

    /*
    Putting the @Lazy annotation directly inside the constructor parameter is a
    highly specific and powerful feature in Spring. It tells Spring: "Do not resolve or
    initialize the actual PaymentService bean right now. Instead, give me a temporary placeholder."

        Here is exactly what happens under the hood when your code runs:

        1. The Proxy Mechanism (What Spring actually injects)
        When Spring boots up, it sees that OrderService is an eager component
        (since @Lazy at the class level is commented out), so it immediately tries to run your constructor.

        However, because you placed @Lazy inside the constructor parameter:

        Spring pauses looking for the real PaymentService implementation.

        It dynamically creates a Proxy Object (a subclass wrapper created at runtime using
        a library called CGLIB) that mimics PaymentService.

        It passes this lightweight proxy into your constructor and prints "Order Service Created".

        At this exact moment, the real object implementing PaymentService has still not been instantiated.
     */
    private  final PaymentService paymentService;
    public OrderService(@Lazy  PaymentService paymentService)
    {
        this.paymentService = paymentService;
        System.out.println("Order Service Created");
    }

    /*
    2. The Activation (When the real object is born)
    The real PaymentService bean will remain asleep in memory until the first time your code
     actually invokes a method on it.
     */

    public void pay()
    {
        paymentService.pay();
    }
}

        /*
        There are two primary reasons why placing @Lazy inside a constructor parameter is incredibly useful:

        A. Performance Optimization
        If PaymentService takes a long time to initialize (e.g., it connects to an external banking API,
        sets up secure SSL certificates, or reads thousands of keys at startup), you don't want to slow down
         your entire application boot time. By using @Lazy in the constructor, OrderService can be created instantly,
         and the heavy payment initialization is delayed until someone actually tries to make a payment.

        B. Breaking Circular Dependencies
        This is a favorite topic for interviewers. Imagine a scenario where OrderService needs PaymentService,
         but PaymentService also needs to autowire OrderService.

        Without @Lazy, Spring gets stuck in an infinite loop trying to create one to satisfy the other,
        throwing a BeanCurrentlyInCreationException.

        By putting @Lazy in the constructor parameter, Spring can instantly create OrderService using a
        fake proxy for the payment service, breaking the cycle and allowing the application to start up smoothly
         */