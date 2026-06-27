package com.abhinav.CircularDependency;


import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private  final OrderService orderService;

    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void pay() {
        System.out.println("Payment Done");

        orderService.getOrderDetails();
    }
}
