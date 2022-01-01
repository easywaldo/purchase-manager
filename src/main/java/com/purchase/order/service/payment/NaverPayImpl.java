package com.purchase.order.service.payment;

import com.purchase.order.command.OrderProductCommand;
import com.purchase.order.response.NaverPayPgResponseImpl;
import com.purchase.order.response.PgResponse;
import lombok.Getter;

@Getter
public class NaverPayImpl implements IPaymentService {

    private final OrderProductCommand command;

    public NaverPayImpl(OrderProductCommand command) {
        this.command = command;
    }

    @Override
    public PgResponse paymentToPg() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var naverPayPgResponse = NaverPayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .build();
        return naverPayPgResponse;
    }

    @Override
    public PgResponse paymentToCancel() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var naverPayPgResponse = NaverPayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .build();
        return naverPayPgResponse;
    }
}
