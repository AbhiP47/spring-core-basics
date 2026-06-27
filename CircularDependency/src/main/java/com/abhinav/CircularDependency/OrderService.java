package com.abhinav.CircularDependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    /*
    To avoid the circular dependency we can use field injection or
     setter injection instead of the constructor injection.
     However circular dependency is not a good practice. We should not solve it; instead we should avoid it
     Spring Core allows circular dependency; however, Spring Boot doesn't allow the circular dependency.
     For a good scalable application there should be no separate dependencies as it is a paired code practice.
     As it Violates the SOLID Design principle and make the code tightly coupled
     
     */

    private final PaymentService paymentService;


    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder()
    {
        paymentService.pay();

        System.out.println("Order Placed");
    }

    public void getOrderDetails() {
        System.out.println("Order Details");
    }
}
