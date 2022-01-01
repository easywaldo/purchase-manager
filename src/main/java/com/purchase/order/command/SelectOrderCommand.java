package com.purchase.order.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectOrderCommand {
    private Integer productSeq;
    private Integer memberSeq;

    @Builder
    public SelectOrderCommand(Integer productSeq,
                              Integer memberSeq) {
        this.productSeq = productSeq;
        this.memberSeq = memberSeq;
    }
}
