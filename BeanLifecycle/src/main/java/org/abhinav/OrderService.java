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

/*
Why We Cannot Replace @PostConstruct with a Constructor?

 The Timeline Conflict (Why fields are null)
In standard Java, you can use a constructor to set up your object because you pass dependencies directly
 into it manually. However, when Spring manages your classes using field injection (@Autowired on top of
 private fields), it has to follow a strict two-step process:

Phase 1 (Instantiation): Spring calls the constructor to physically allocate memory and create the raw
 Java object instance.

Phase 2 (Populate Properties): Only after the object is created does Spring use reflection to scan your
fields and inject the dependencies.

If you attempt to use a dependency inside a constructor, your application will crash with a NullPointerException



The Exception: Constructor Injection
There is one exception to this rule. If you switch from field injection to Constructor Injection, you can use the
 constructor for setup because you are forcing Spring to find the dependencies before calling the constructor

 Even if the fields aren't null during constructor injection, complex business setup (like starting a background
  thread execution, scheduling a cron job, or loading heavy data from a database) is considered a bad practice inside constructors.

A constructor's design role under the SOLID principles is strictly to assign values to fields. Heavy lifting,
 external network calls, or logic validations should always be deferred to an initialization lifecycle method.
 */