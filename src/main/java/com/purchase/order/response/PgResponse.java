package com.purchase.order.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public abstract class PgResponse {
    private boolean isSuccess = false;
    private String txTid;
    private String resultCode;
    private String resultMessage;
    private UUID orderId;
    private Integer orderPaymentSeq;

    public void setOrderInfo(UUID orderId, Integer orderPaymentSeq) {
        this.orderId = orderId;
        this.orderPaymentSeq = orderPaymentSeq;
    }
}
