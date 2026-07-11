package org.abhinav;

import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

    private PaymentProperties paymentProperties;

    public PaymentGateway(PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
    }

    public String getType() {
        return paymentProperties.getType();
    }

    public int getRetryCount() {
        return paymentProperties.getRetryCount();
    }

    public boolean isEnabled() {
        return paymentProperties.isEnabled();
    }

    public int getTimeout() {
        return paymentProperties.getTimeout();
    }

    public void print() {
        System.out.println(getType());
        System.out.println(getRetryCount());
        System.out.println(isEnabled());
        System.out.println(getTimeout());
    }
}


// @Value

//    @Value("${paymentGateway.type:Razorpay}")
//    private String type;
//
//    @Value("${paymentGateway.retry-count:3}")
//    private int retryCount;

/*
CommandLineRunner and ApplicationRunner are functional interfaces in Spring Boot that let you run
code right after the application context is fully loaded and the application has started — but before
it starts accepting requests/finishes startup completely. They're commonly used for startup tasks.
Both live in the org.springframework.boot package and have a single method to implement.
Why they exist (significance)
Sometimes you need to run a piece of logic exactly once, automatically, when the app boots
 up — things like:

Loading initial/seed data into a database
Validating configuration or external connections at startup
Printing startup logs/diagnostics
Running batch jobs or one-time setup scripts
Warming up caches

Without these interfaces, you'd have to hack this into a constructor or a static block,
 which doesn't have access to a fully initialized Spring context. These runners guarantee
  the entire Spring context (all beans) is ready before your code executes.

  ApplicationRunner differs by giving structured, parsed access to command-line arguments
  via ApplicationArguments, while CommandLineRunner gives raw string arguments.
 */