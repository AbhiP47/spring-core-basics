package com.abhinav.transactions.service;

import com.abhinav.transactions.entity.Order;
import com.abhinav.transactions.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  PaymentAuditService paymentAuditService;


    @Transactional
    public void placeOrder(Order order)
    {
            orderRepository.save(order);
            paymentAuditService.audit(order);
    }
}
