package com.purchase.order.controller;

import com.purchase.order.command.SelectOrderCommand;
import com.purchase.order.service.MyOrderInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OrderSelectController {
    private final MyOrderInfoService myOrderInfoService;

    @Autowired
    public OrderSelectController(MyOrderInfoService myOrderInfoService) {
        this.myOrderInfoService = myOrderInfoService;
    }

    @ApiOperation(value = "상품주문상세 를 조회합니다.", notes = "회원의 해당상품에 대한 주문내역들을 조회합니다.")
    @PostMapping(value = "/order/product/detail")
    public Mono<ResponseEntity<?>> selectOrderProduct(@RequestBody SelectOrderCommand selectOrderCommand) {
        var result = this.myOrderInfoService.selectOrderProduct(selectOrderCommand);
        return Mono.just(ResponseEntity.ok().body(result));
    }

    @ApiOperation(value = "회원상품주문리스트 를 조회합니다.", notes = "회원에 대한 구매한 상품내역들을 조회합니다.")
    @PostMapping(value = "/order/product/list")
    public Mono<ResponseEntity<?>> selectOrderList(@RequestBody SelectOrderCommand selectOrderCommand) {
        var result = this.myOrderInfoService.selectOrderList(selectOrderCommand);
        return Mono.just(ResponseEntity.ok().body(result));
    }
}
