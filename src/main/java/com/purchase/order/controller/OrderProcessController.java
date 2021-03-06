package com.purchase.order.controller;

import com.purchase.order.command.OrderProductCommand;
import com.purchase.order.command.OrderValidator;
import com.purchase.order.response.OrderProcessResponse;
import com.purchase.order.service.OrderProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class OrderProcessController {
    private final OrderProductService orderProductService;
    private final Validator validator;

    @Autowired
    public OrderProcessController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
        this.validator = new OrderValidator();
    }

    @ApiOperation(value = "상품 주문을 요청 합니다.", notes = "상품을 선택하여 구매 지불이 완료된 상태에서 주문완료 요청을 진행합니다.")
    @PostMapping(value = "/order/")
    public Mono<ResponseEntity<?>> processOrder(@RequestBody OrderProductCommand orderProductCommand,
                                                @ApiIgnore Errors errors) {

        this.validator.validate(orderProductCommand, errors);
        if (errors.hasErrors()) {
            return Mono.just(ResponseEntity.internalServerError().body(errors));
        }

        var pgResponse = this.orderProductService.processOrder(orderProductCommand);
        return Mono.just(ResponseEntity.ok().body(OrderProcessResponse.builder()
            .pgResponse(pgResponse)
            .orderProductCommand(orderProductCommand)
            .build()));
    }
}
