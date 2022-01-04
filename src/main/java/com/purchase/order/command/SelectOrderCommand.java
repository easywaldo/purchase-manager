package com.purchase.order.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectOrderCommand {
    @ApiModelProperty(value = "상품아이디(일련번호)", position = 1)
    private Integer productSeq;
    @ApiModelProperty(value = "구매회원번호", position = 2)
    private Integer memberSeq;

    @Builder
    public SelectOrderCommand(Integer productSeq,
                              Integer memberSeq) {
        this.productSeq = productSeq;
        this.memberSeq = memberSeq;
    }
}
