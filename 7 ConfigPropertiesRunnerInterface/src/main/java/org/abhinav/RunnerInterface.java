package org.abhinav;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerInterface implements CommandLineRunner /*ApplicationRunner*/ {

    private PaymentGateway paymentGateway;

    public RunnerInterface(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void run(String... args) throws Exception {
        paymentGateway.print();
    }



//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        paymentGateway.print();
//    }
}
