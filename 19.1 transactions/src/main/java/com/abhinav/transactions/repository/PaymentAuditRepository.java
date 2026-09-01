package com.abhinav.transactions.repository;

import com.abhinav.transactions.entity.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAuditRepository extends JpaRepository<PaymentAudit, Long> {
}