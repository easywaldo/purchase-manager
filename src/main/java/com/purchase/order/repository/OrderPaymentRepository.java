package com.purchase.order.repository;

import com.purchase.order.entity.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Integer> {
    OrderPayment findByOrderId(UUID orderId);
}
