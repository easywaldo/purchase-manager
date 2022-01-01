package com.purchase.order.service.payment;

import com.purchase.order.command.OrderProductCommand;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactory {
    public static IPaymentService getPgService(OrderProductCommand orderProductCommand) {
        switch (orderProductCommand.getPgType()) {
            case 0: return new KakaoPayImpl(orderProductCommand);
            case 1: return new NaverPayImpl(orderProductCommand);
            case 3: return new TossPayImpl(orderProductCommand);
            case 2:
            default: return new NicePaymentImpl(orderProductCommand);
        }
    }
}
