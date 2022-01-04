package com.purchase.product.command;

import com.purchase.category.repository.CategoryRepository;
import com.purchase.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class RegisterProductCommand {
    @ApiModelProperty(value = "상품이름", position = 1)
    @NotBlank
    private String productName;
    @ApiModelProperty(value = "카테고리번호", position = 2)
    @NotNull
    private Integer categorySeq;
    @ApiModelProperty(value = "상품가격", position = 3)
    @NotNull
    private Integer productPrice;
    @ApiModelProperty(value = "상품설명", position = 4)
    @NotBlank
    private String productDescription;

    @Builder
    public RegisterProductCommand(String productName,
                                  Integer categorySeq,
                                  Integer productPrice,
                                  String productDescription) {
        this.productName = productName;
        this.categorySeq = categorySeq;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public Product toEntity(CategoryRepository categoryRepository) {
        return Product.builder()
            .productName(this.productName)
            .productPrice(this.productPrice)
            .productDescription(this.productDescription)
            .category(categoryRepository.findById(this.getCategorySeq()).get())
            .build();
    }
}
