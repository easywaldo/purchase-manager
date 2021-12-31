package com.purchase.order.service.payment;

import com.purchase.order.response.PgResponse;

public interface IPaymentService {
    PgResponse paymentToPg();
    PgResponse paymentToCancel();
}
