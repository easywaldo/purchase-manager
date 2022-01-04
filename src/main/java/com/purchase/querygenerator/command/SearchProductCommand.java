package com.purchase.querygenerator.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchProductCommand {
    @ApiModelProperty(value = "카테고리 이름", position = 1)
    private String categoryName;
    @ApiModelProperty(value = "상품아이디(일련번호)", position = 2)
    private Integer productSeq;
    @ApiModelProperty(value = "카테고리 대분류", position = 3)
    private String categoryLargeCode;
    @ApiModelProperty(value = "카테고리 중분류", position = 4)
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
