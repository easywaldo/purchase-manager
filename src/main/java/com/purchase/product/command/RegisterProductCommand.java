package com.purchase.product.command;

import com.purchase.category.repository.CategoryRepository;
import com.purchase.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class RegisterProductCommand {
    @NotBlank
    private String productName;
    @NotNull
    private Integer categorySeq;
    @NotNull
    private Integer productPrice;
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
            .categorySeq(categoryRepository.findById(this.getCategorySeq()).get())
            .build();
    }
}
