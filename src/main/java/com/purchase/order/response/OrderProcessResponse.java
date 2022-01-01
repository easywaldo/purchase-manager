package com.purchase.order.response;

import com.purchase.order.command.OrderProductCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProcessResponse {
    private OrderProductCommand orderProductCommand;
    private PgResponse pgResponse;

    @Builder
    public OrderProcessResponse(OrderProductCommand orderProductCommand,
                                PgResponse pgResponse) {
        this.orderProductCommand = orderProductCommand;
        this.pgResponse = pgResponse;
    }
}
