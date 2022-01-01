package com.purchase.order.exception;

import com.purchase.order.response.PgResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProcessException extends RuntimeException {
    private String pgTransactionId;
    private PgResponse pgResponse;

    @Builder
    public OrderProcessException(String pgTransactionId,
                                 PgResponse pgResponse) {
        this.pgTransactionId = pgTransactionId;
        this.pgResponse = pgResponse;
        failedOrderPgCancel();
    }

    public void failedOrderPgCancel() {
        this.pgResponse.paymentCancelToPg();
    }
}
