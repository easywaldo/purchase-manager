package com.purchase.order.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "order_payment")
public class OrderPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_payment_seq")
    private Integer orderPaymentSeq;

    @Column(name = "pg_tx_tid")
    private String txTid;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "member_seq")
    private Integer memberSeq;

    @Builder
    public OrderPayment(Integer orderPaymentSeq,
                        String txTid,
                        UUID orderId,
                        Integer memberSeq) {
        this.orderPaymentSeq = orderPaymentSeq;
        this.txTid = txTid;
        this.orderId = orderId;
        this.memberSeq = memberSeq;
    }
}
