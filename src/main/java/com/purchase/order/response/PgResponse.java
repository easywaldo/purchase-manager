package com.purchase.order.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class PgResponse {
    private boolean isSuccess = false;
    private String txTid;
    private String resultCode;
    private String resultMessage;

    private void paymentToPg() {
        this.isSuccess = true;
    }
}
