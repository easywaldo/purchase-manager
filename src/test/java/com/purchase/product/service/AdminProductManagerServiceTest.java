package com.purchase.product.service;

import com.purchase.category.repository.CategoryRepository;
import com.purchase.product.command.RegisterProductCommand;
import com.purchase.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AdminProductManagerServiceTest {

    @Autowired
    private AdminProductManagerService adminProductManagerService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void 어드민_서비스를_통해_상품이_정상_등록되는지를_확인한다() {
        //arrange
        RegisterProductCommand registerProductCommand = RegisterProductCommand.builder()
            .productPrice(90000)
            .productName("TEST PRODUCT")
            .productDescription("TEST PRODUCT CONTENT")
            .categorySeq(10)
            .build();

        // act
        var savedProduct = this.adminProductManagerService.registerProduct(registerProductCommand);

        // assert
        var result = this.productRepository.findAll();
        assertEquals(true, result.stream().anyMatch(x -> x.getProductName().equals("TEST PRODUCT")));
        assertEquals(90000, savedProduct.getProductPrice());
    }


}