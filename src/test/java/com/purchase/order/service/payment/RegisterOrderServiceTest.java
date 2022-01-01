package com.purchase.order.service.payment;

import com.purchase.order.repository.OrderInfoRepository;
import com.purchase.order.response.NaverPayPgResponseImpl;
import com.purchase.order.response.PgResponse;
import com.purchase.product.command.OrderProductCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegisterOrderServiceTest {

    @Autowired
    private RegisterOrderService registerOrderService;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Test
    public void 주문_요청에_대한_주문전표_생성이_정상적으로_이뤄지는지_확인한다() {
        // arrange
        OrderProductCommand orderProductCommand = OrderProductCommand.builder()
            .memberSeq(1)
            .pgType(1)
            .pgTransactionId("test-tid-001")
            .orderItems(List.of(OrderProductCommand.OrderItem.builder()
                .productCount(1)
                .productSeq(1)
                .build()))
            .build();
        PgResponse pgResponse = new NaverPayPgResponseImpl();

        // act
        var result = this.registerOrderService.processOrder(orderProductCommand, pgResponse);

        // assert
        assertNotNull(result);
        assertNotNull(result.getOrderId());
        assertEquals(1, orderInfoRepository.findByOrderPaymentSeq(result).size());
    }


}