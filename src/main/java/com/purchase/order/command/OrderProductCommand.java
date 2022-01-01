package com.purchase.order.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderProductCommand {

    private Integer pgType;
    private String pgTransactionId;
    private Integer memberSeq;
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public OrderProductCommand(Integer pgType,
                               String pgTransactionId,
                               Integer memberSeq,
                               List<OrderItem> orderItems) {
        this.pgType = pgType;
        this.pgTransactionId = pgTransactionId;
        this.memberSeq = memberSeq;
        this.orderItems = orderItems;
    }

    @Getter
    @NoArgsConstructor
    public static class OrderItem {
        private Integer productSeq;
        private Integer productCount;

        @Builder
        public OrderItem(Integer productSeq, Integer productCount) {
            this.productSeq = productSeq;
            this.productCount = productCount;
        }
    }
}
