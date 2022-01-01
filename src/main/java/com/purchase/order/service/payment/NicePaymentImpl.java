package com.purchase.order.service.payment;

import com.purchase.order.command.OrderProductCommand;
import com.purchase.order.response.NicePayPgResponseImpl;
import com.purchase.order.response.PgResponse;
import lombok.Getter;

@Getter
public class NicePaymentImpl implements IPaymentService{
    private final OrderProductCommand command;

    public NicePaymentImpl(OrderProductCommand command) {
        this.command = command;
    }

    @Override
    public PgResponse paymentToPg() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var nicePayment = NicePayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .isSuccess(true)
            .build();
        return nicePayment;
    }

    @Override
    public PgResponse paymentToCancel() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var nicePayment = NicePayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .build();
        return nicePayment;
    }
}
