package com.purchase.order.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderProductCommand {
    @ApiModelProperty(value = "PG사 타입(예: 나이스페이, 토스페이, 카카오페이, 네이버페이 등", position = 1)
    @NotNull
    private Integer pgType;
    @ApiModelProperty(value = "PG사 트랜잭션 아이디", position = 2)
    @NotBlank
    private String pgTransactionId;
    @ApiModelProperty(value = "회원번호", position = 3)
    @NotNull
    private Integer memberSeq;
    @ApiModelProperty(value = "주문요청 상품 리스트", position = 4)
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
