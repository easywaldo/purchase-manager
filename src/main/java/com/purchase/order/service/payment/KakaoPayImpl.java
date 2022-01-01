package com.purchase.order.service.payment;

import com.purchase.order.command.OrderProductCommand;
import com.purchase.order.response.KakaoPayPgResponseImpl;
import com.purchase.order.response.PgResponse;
import lombok.Getter;

@Getter
public class KakaoPayImpl implements IPaymentService {
    private final OrderProductCommand command;

    public KakaoPayImpl(OrderProductCommand command) {
        this.command = command;
    }

    @Override
    public PgResponse paymentToPg() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var kakaoPayResponse = KakaoPayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .build();
        return kakaoPayResponse;
    }

    @Override
    public PgResponse paymentToCancel() {
        // TODO: 실제 PG 구현이 되어야 할 부분
        var kakaoPayResponse = KakaoPayPgResponseImpl.builder()
            .txTid(command.getPgTransactionId())
            .build();
        return kakaoPayResponse;
    }
}
