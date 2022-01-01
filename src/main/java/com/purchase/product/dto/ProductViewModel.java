package com.purchase.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductViewModel {
    private Integer productSeq;
    private String productName;
    private Integer categorySeq;
    private String categoryName;
    private Integer productPrice;

    @Builder
    public ProductViewModel(Integer productSeq,
                            String productName,
                            Integer categorySeq,
                            String categoryName,
                            Integer productPrice) {
        this.productSeq = productSeq;
        this.productName = productName;
        this.categorySeq = categorySeq;
        this.categoryName = categoryName;
        this.productPrice = productPrice;
    }
}
