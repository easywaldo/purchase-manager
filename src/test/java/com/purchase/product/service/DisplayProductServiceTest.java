package com.purchase.product.service;

import com.purchase.querygenerator.command.SearchProductCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DisplayProductServiceTest {
    @Autowired
    private DisplayProductService displayProductService;

    @Test
    public void 상품_리스트를_상품_전시서비스에서_조회가_되는지_확인한다() {
        // arrange
        SearchProductCommand searchCommand = SearchProductCommand.builder()
            .productSeq(1)
            .build();

        // act
        var result = this.displayProductService.selectProductList(searchCommand);

        // assert
        assertEquals(1, result.size());
        assertEquals(1, result.stream().findFirst().get().getProductSeq());
    }
}