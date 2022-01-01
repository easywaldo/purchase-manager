package com.purchase.order.controller;

import com.purchase.order.command.OrderProductCommand;
import com.purchase.order.response.OrderProcessResponse;
import com.purchase.order.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OrderProcessController {
    private final OrderProductService orderProductService;

    @Autowired
    public OrderProcessController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @PostMapping(value = "/order/")
    public Mono<ResponseEntity<?>> processOrder(@RequestBody OrderProductCommand orderProductCommand) {
        var pgResponse = this.orderProductService.processOrder(orderProductCommand);
        return Mono.just(ResponseEntity.ok().body(OrderProcessResponse.builder()
            .pgResponse(pgResponse)
            .orderProductCommand(orderProductCommand)
            .build()));
    }
}
