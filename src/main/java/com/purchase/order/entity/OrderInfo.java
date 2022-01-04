package com.purchase.order.entity;

import com.purchase.member.entity.Member;
import com.purchase.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "order_info")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_info_seq")
    private Integer orderInfoSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_payment_seq", referencedColumnName = "order_payment_seq")
    private OrderPayment orderPaymentSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq", referencedColumnName = "member_seq")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq", referencedColumnName = "product_seq")
    private Product product;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "payment_price")
    private Integer paymentPrice;

    @Builder
    public OrderInfo(Integer orderInfoSeq,
                     OrderPayment orderPaymentSeq,
                     Member member,
                     Product product,
                     LocalDateTime orderDate,
                     Integer productCount,
                     Integer paymentPrice) {
        this.orderInfoSeq = orderInfoSeq;
        this.orderPaymentSeq = orderPaymentSeq;
        this.member = member;
        this.product = product;
        this.orderDate = orderDate;
        this.productCount = productCount;
        this.paymentPrice = paymentPrice;
    }
}
