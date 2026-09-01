package com.abhinav.transactions.service;

import com.abhinav.transactions.entity.Order;
import com.abhinav.transactions.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  PaymentAuditService paymentAuditService;


    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            timeout = 30
    )
    public void placeOrder(Order order)
    {
            orderRepository.save(order);
            paymentAuditService.audit(order);

    }
}
