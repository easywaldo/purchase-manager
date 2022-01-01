package com.purchase.order.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class NicePayPgResponseImpl extends PgResponse {
    private boolean isSuccess = false;
    private String txTid;
    private String resultCode;
    private String resultMessage;
    private UUID orderId;
    private Integer orderPaymentSeq;

    @Builder
    public NicePayPgResponseImpl(boolean isSuccess,
                                 String txTid,
                                 String resultCode,
                                 String resultMessage,
                                 UUID orderId,
                                 Integer orderPaymentSeq) {
        this.isSuccess = isSuccess;
        this.txTid = txTid;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.orderId = orderId;
        this.orderPaymentSeq = orderPaymentSeq;
    }
}
