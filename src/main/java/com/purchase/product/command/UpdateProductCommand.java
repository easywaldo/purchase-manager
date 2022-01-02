package com.purchase.product.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateProductCommand {
    @NotNull
    private Integer productSeq;
    @NotBlank
    private String productName;
    @NotNull
    private Integer categorySeq;
    @NotNull
    private Integer productPrice;
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
