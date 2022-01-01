package com.purchase.order.service;

import com.purchase.order.response.PgResponse;
import com.purchase.order.service.payment.IPaymentService;
import com.purchase.order.service.payment.PaymentFactory;
import com.purchase.order.service.payment.RegisterOrderService;
import com.purchase.order.command.OrderProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProductService {
    private final RegisterOrderService registerOrderService;

    @Autowired
    public OrderProductService(RegisterOrderService registerOrderService) {
        this.registerOrderService = registerOrderService;
    }

    @Transactional
    public PgResponse processOrder(OrderProductCommand orderProductCommand) {
        IPaymentService paymentService = PaymentFactory.getPgService(orderProductCommand);
        PgResponse pgResponse = paymentService.paymentToPg();
        if (pgResponse.isSuccess()) {
            var orderPayment = registerOrderService.processOrder(orderProductCommand, paymentService, pgResponse);
            pgResponse.setOrderInfo(orderPayment.getOrderId(), orderPayment.getOrderPaymentSeq());
        }
        return pgResponse;
    }
}
