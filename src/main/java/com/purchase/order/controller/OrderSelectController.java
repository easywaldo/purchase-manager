package com.purchase.order.controller;

import com.purchase.order.command.SelectOrderCommand;
import com.purchase.order.service.MyOrderInfoService;
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

    @PostMapping(value = "/order/product/detail")
    public Mono<ResponseEntity<?>> selectOrderProduct(@RequestBody SelectOrderCommand selectOrderCommand) {
        var result = this.myOrderInfoService.selectOrderProduct(selectOrderCommand);
        return Mono.just(ResponseEntity.ok().body(result));
    }

    @PostMapping(value = "/order/product/list")
    public Mono<ResponseEntity<?>> selectOrderList(@RequestBody SelectOrderCommand selectOrderCommand) {
        var result = this.myOrderInfoService.selectOrderList(selectOrderCommand);
        return Mono.just(ResponseEntity.ok().body(result));
    }
}
