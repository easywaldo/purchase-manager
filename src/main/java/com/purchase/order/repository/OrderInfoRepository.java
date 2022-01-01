package com.purchase.order.repository;

import com.purchase.order.entity.OrderInfo;
import com.purchase.order.entity.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findByOrderPaymentSeq(OrderPayment orderPayment);
}
