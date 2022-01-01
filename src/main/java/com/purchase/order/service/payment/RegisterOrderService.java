package com.purchase.order.service.payment;

import com.purchase.member.repository.MemberRepository;
import com.purchase.order.entity.OrderInfo;
import com.purchase.order.entity.OrderPayment;
import com.purchase.order.exception.OrderProcessException;
import com.purchase.order.repository.OrderInfoRepository;
import com.purchase.order.repository.OrderPaymentRepository;
import com.purchase.order.response.PgResponse;
import com.purchase.product.command.OrderProductCommand;
import com.purchase.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegisterOrderService {

    private final OrderInfoRepository orderInfoRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderPaymentRepository orderPaymentRepository;

    @Autowired
    public RegisterOrderService(OrderInfoRepository orderInfoRepository,
                                ProductRepository productRepository,
                                MemberRepository memberRepository,
                                OrderPaymentRepository orderPaymentRepository) {
        this.orderInfoRepository = orderInfoRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Transactional(rollbackFor = OrderProcessException.class)
    public OrderPayment processOrder(OrderProductCommand orderProductCommand,
                                     PgResponse pgResponse) {
        try {
            var result = this.orderPaymentRepository.save(OrderPayment.builder()
                .orderId(UUID.randomUUID())
                .txTid(orderProductCommand.getPgTransactionId())
                .memberSeq(orderProductCommand.getMemberSeq())
                .build());
            this.orderInfoRepository.saveAll(orderProductCommand.getOrderItems().stream().collect(Collectors.toList())
                .stream().map(x -> OrderInfo.builder()
                    .productSeq(this.productRepository.findById(x.getProductSeq()).get())
                    .orderDate(LocalDateTime.now())
                    .orderPaymentSeq(result)
                    .paymentPrice(this.productRepository.findById(x.getProductSeq()).get().getProductPrice() * x.getProductCount())
                    .memberSeq(this.memberRepository.findById(orderProductCommand.getMemberSeq()).get())
                    .build()).collect(Collectors.toList()));
            return result;
        }
        catch(Exception ex) {
            throw OrderProcessException.builder()
                .pgTransactionId(orderProductCommand.getPgTransactionId())
                .pgResponse(pgResponse)
                .build();
        }
    }
}
