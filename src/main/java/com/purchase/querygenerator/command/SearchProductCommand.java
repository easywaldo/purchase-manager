package com.purchase.querygenerator.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchProductCommand {
    private String categoryName;
    private Integer productSeq;
    private String categoryLargeCode;
    private String categoryMediumCode;

    @Builder
    public SearchProductCommand(String categoryName,
                                Integer productSeq,
                                String categoryLargeCode,
                                String categoryMediumCode) {
        this.categoryName = categoryName;
        this.productSeq = productSeq;
        this.categoryLargeCode = categoryLargeCode;
        this.categoryMediumCode = categoryMediumCode;
    }
}
