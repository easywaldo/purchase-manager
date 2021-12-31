package com.purchase.product.service;

import com.purchase.order.service.payment.PaymentFactory;
import com.purchase.order.service.payment.RegisterOrderService;
import com.purchase.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {
    private final ProductRepository productRepository;
    private final PaymentFactory paymentFactory;
    private final RegisterOrderService registerOrderService;

    @Autowired
    public OrderProductService(ProductRepository productRepository,
                               PaymentFactory paymentFactory,
                               RegisterOrderService registerOrderService) {
        this.productRepository = productRepository;
        this.paymentFactory = paymentFactory;
        this.registerOrderService = registerOrderService;
    }
}
