package com.purchase.order.service.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentFactory {
    public static IPaymentService getPgService(int pgType) {
        switch (pgType) {
            case 0: return new KakaoPayImpl();
            case 1: return new NaverPayImpl();
            case 3: return new TossPayImpl();
            case 2:
            default: return new NicePaymentImpl();
        }
    }
}
