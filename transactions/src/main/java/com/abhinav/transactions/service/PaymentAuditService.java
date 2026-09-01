package com.abhinav.transactions.service;

import com.abhinav.transactions.entity.Order;
import com.abhinav.transactions.entity.PaymentAudit;
import com.abhinav.transactions.repository.PaymentAuditRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentAuditService {

    private final PaymentAuditRepository paymentAuditRepository;

    public PaymentAuditService(PaymentAuditRepository paymentAuditRepository) {
        this.paymentAuditRepository = paymentAuditRepository;
    }

    public void audit(Order order) {

        PaymentAudit paymentAudit =
                new PaymentAudit(order.getAmount() , order.getId(), true);
        paymentAuditRepository.save(paymentAudit);
    }
}
