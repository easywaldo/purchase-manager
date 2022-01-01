package com.purchase.querygenerator;

import com.purchase.order.command.SelectOrderCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderQueryGeneratorTest {
    @Autowired
    private OrderQueryGenerator orderQueryGenerator;

    @Test
    public void 주문상품_내역들이_주문쿼리제너레이터를_통해_리턴이_되는지_확인한다() {
        // arrange
        SelectOrderCommand selectOrderCommand = SelectOrderCommand.builder()
            .productSeq(1)
            .memberSeq(1)
            .build();

        // act
        var result = this.orderQueryGenerator.selectOrderProductDetail(selectOrderCommand);

        // assert
        assertNotNull(result);
    }

}