package com.abhinav.transactions.repository;

import com.abhinav.transactions.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}