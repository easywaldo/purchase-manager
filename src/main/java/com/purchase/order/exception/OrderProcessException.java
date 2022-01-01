package com.purchase.order.exception;

import com.purchase.order.response.PgResponse;
import com.purchase.order.service.payment.IPaymentService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProcessException extends RuntimeException {
    private PgResponse pgResponse;
    private IPaymentService paymentService;

    @Builder
    public OrderProcessException(IPaymentService paymentService,
                                 PgResponse pgResponse) {
        this.paymentService = paymentService;
        this.pgResponse = pgResponse;
        failedOrderPgCancel(paymentService);
    }

    public void failedOrderPgCancel(IPaymentService paymentService) {
        this.paymentService.paymentToCancel();
    }
}
