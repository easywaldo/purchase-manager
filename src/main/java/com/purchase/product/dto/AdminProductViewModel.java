package com.purchase.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminProductViewModel {
    private Integer productSeq;
    private Integer categorySeq;
    private String categoryName;
    private String productName;
    private String productDescription;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    @Builder
    public AdminProductViewModel(Integer productSeq,
                                 Integer categorySeq,
                                 String categoryName,
                                 String productName,
                                 String productDescription,
                                 LocalDateTime createDt,
                                 LocalDateTime updateDt) {
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productDescription = productDescription;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
