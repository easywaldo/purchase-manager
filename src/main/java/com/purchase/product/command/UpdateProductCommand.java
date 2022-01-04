package com.purchase.product.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateProductCommand {
    @ApiModelProperty(value = "상품번호", position = 1)
    @NotNull
    private Integer productSeq;
    @ApiModelProperty(value = "상품명", position = 2)
    @NotBlank
    private String productName;
    @ApiModelProperty(value = "카테고리 일련번호", position = 3)
    @NotNull
    private Integer categorySeq;
    @ApiModelProperty(value = "상품가격", position = 4)
    @NotNull
    private Integer productPrice;
    @ApiModelProperty(value = "상품설명", position = 5)
    @NotBlank
    private String productDescription;

    @Builder
    public UpdateProductCommand(Integer productSeq,
                                String productName,
                                Integer categorySeq,
                                Integer productPrice,
                                String productDescription) {
        this.productSeq = productSeq;
        this.productName = productName;
        this.categorySeq = categorySeq;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }
}
