package com.purchase.order.controller;

import autofixture.publicinterface.Fixture;
import com.purchase.member.service.AuthService;
import com.purchase.order.command.OrderProductCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class OrderProcessControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AuthService authService;

    @Test
    public void 상품_구매_요청이_30개_초과할_수_없는지를_확인한다() throws UnsupportedEncodingException {
        // arrange
        Fixture fixture = new Fixture();
        List<OrderProductCommand.OrderItem> items = new ArrayList<>();
        for (Integer i : IntStream.range(0, 40).boxed().collect(Collectors.toList())) {
            items.add(fixture.create(OrderProductCommand.OrderItem.class));
        }
        OrderProductCommand orderProductCommand = OrderProductCommand.builder()
            .orderItems(items)
            .pgType(1)
            .memberSeq(1)
            .pgTransactionId("test-pg-tx-tid")
            .build();

        var token = authService.issueToken("tester-001");

        // assert
        this.webTestClient.post().uri("/order/")
            .cookie("userJwt", token)
            .header("Authorization", "Bearer " + token)
            .bodyValue(orderProductCommand)
            .exchange()
            .expectStatus().is5xxServerError();
    }



}