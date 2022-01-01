package com.purchase.order.service.payment;

import com.purchase.order.command.OrderProductCommand;
import com.purchase.order.response.PgResponse;
import com.purchase.order.response.TossPayPgResponseImpl;

public class TossPayImpl implements IPaymentService {

    private final OrderProductCommand command;

    public TossPayImpl(OrderProductCommand command) {
        this.command = command;
    }

    @Override
    public PgResponse paymentToPg() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var tossPayPgResponse = TossPayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .build();
        return tossPayPgResponse;
    }

    @Override
    public PgResponse paymentToCancel() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var tossPayPgResponse = TossPayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .build();
        return tossPayPgResponse;
    }
}
