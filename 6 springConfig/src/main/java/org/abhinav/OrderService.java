package org.abhinav;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    public void placeOrder()
    {
        paymentService.pay();
        System.out.println("Order Placed");
    }

    @PostConstruct
    public void postConstructMethod()
    {
        System.out.println("Order service Post Construct Called");
    }
}