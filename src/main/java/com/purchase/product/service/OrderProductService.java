package com.purchase.product.service;

import com.purchase.order.repository.OrderInfoRepository;
import com.purchase.order.response.PgResponse;
import com.purchase.order.service.payment.PaymentFactory;
import com.purchase.order.service.payment.RegisterOrderService;
import com.purchase.product.command.OrderProductCommand;
import com.purchase.product.repository.ProductRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {
    private final ProductRepository productRepository;
    private final PaymentFactory paymentFactory;
    private final RegisterOrderService registerOrderService;
    private final OrderInfoRepository orderInfoRepository;

    @Builder
    public OrderProductService(ProductRepository productRepository,
                               PaymentFactory paymentFactory,
                               RegisterOrderService registerOrderService,
                               OrderInfoRepository orderInfoRepository) {
        this.productRepository = productRepository;
        this.paymentFactory = paymentFactory;
        this.registerOrderService = registerOrderService;
        this.orderInfoRepository = orderInfoRepository;
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
