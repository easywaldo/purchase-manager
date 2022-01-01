package com.purchase.querygenerator;

import com.purchase.querygenerator.command.SearchProductCommand;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductQueryGeneratorTest {

    @Autowired
    private ProductQueryGenerator productQueryGenerator;

    @Test
    public void 상품_리스트를_조회하는_경우_쿼리_제너레이터를_호출하여_결과값을_확인한다() {
        // given
        SearchProductCommand command = SearchProductCommand.builder()
            .categoryLargeCode("001")
            .productSeq(0)
            .build();

        // act
        var productList = productQueryGenerator.selectProductList(command);

        // assert
        assertNotNull(productList);
        assertEquals(true, productList.stream().allMatch(x -> !Strings.isNullOrEmpty(x.getProductName())));
    }

}