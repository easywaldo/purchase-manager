package com.purchase.product.service;

import com.purchase.order.response.PgResponse;
import com.purchase.order.service.payment.PaymentFactory;
import com.purchase.order.service.payment.RegisterOrderService;
import com.purchase.product.command.OrderProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {
    private final RegisterOrderService registerOrderService;

    @Autowired
    public OrderProductService(RegisterOrderService registerOrderService) {
        this.registerOrderService = registerOrderService;
    }

    public PgResponse processOrder(OrderProductCommand orderProductCommand) {
        PgResponse pgResponse = PaymentFactory.getPgService(orderProductCommand.getPgType()).paymentToPg();
        if (pgResponse.isSuccess()) {
            var orderPayment = registerOrderService.processOrder(orderProductCommand, pgResponse);
            pgResponse.setOrderInfo(orderPayment.getOrderId(), orderPayment.getOrderPaymentSeq());
        }
        return pgResponse;
    }
}
