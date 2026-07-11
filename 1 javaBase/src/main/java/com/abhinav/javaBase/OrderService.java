package com.abhinav.javaBase;


import com.abhinav.javaBase.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    // we can write final only when we use constructor injection which is a recommended approach
    private final PaymentService paymentService;

    /*
    Constructor injection is recommended because the dependency gets wired at the time of the object creation.
     Final can be used.
      Easy to test the class. -> In this example if we are testing the order service and we use constructor injection,
       we can give a fake payment service object to the order service constructor to test the functionality without actually making the real payment object.
       And this cannot be done with the setter injection and the field injection.
     */
    @Autowired
    // @Autowired is required when we have more than one constructor
    public OrderService(@Qualifier("cardPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /*
    Since we have two classes implementing the payment service, that is, card payment and UPI payment
    And both the classes are using the @Component annotation
    so when the Spring tries to inject a payment service object into the order service by constructor injection,
     it gets confused and gives an error as :
              No qualifying bean of type 'com.abhinav.javaBase.payment.PaymentService' available:
              expected single matching bean but found 2: cardPayment,upiPayment

     In this situation we have two types of annotations in the Spring Framework,
      which help us to tell Spring which of the two  objects we need to inject  for the order service.
      i.e. @Primary and @Qualifier
      We can add @Primary annotation to the class which we want to keep as priority while creating its object of the payment service.
      On the other hand we can use @Qualifier annotation with all the classes of the payment service type.
       We can use the @Qualifier annotation with the bean name (class name in camel case) in the constructor injection to
       inject the specific object we want of the payment service type in our Order Service.
     */

    /*
    Using both card payment and UPI payment as the object in our order service

    private final PaymentService cardPaymentService;
    private final PaymentService upiPaymentService;

    // Inject both implementations side-by-side
    public OrderService(
            @Qualifier("cardPayment") PaymentService cardPaymentService,
            @Qualifier("upiPayment") PaymentService upiPaymentService) {

        this.cardPaymentService = cardPaymentService;
        this.upiPaymentService = upiPaymentService;
    }

    public void processOrder(double amount, String paymentType) {
        if ("UPI".equalsIgnoreCase(paymentType)) {
            upiPaymentService.pay(amount);
        } else {
            cardPaymentService.pay(amount);
        }
    }
     */
    public void placeOrder()
        {
            paymentService.pay();
        System.out.println("Order Placed");

    }
}
