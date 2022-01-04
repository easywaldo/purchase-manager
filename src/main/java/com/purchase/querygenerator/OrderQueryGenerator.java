package com.purchase.querygenerator;

import com.purchase.order.command.SelectOrderCommand;
import com.purchase.order.entity.QOrderPayment;
import com.purchase.order.response.OrderViewModel;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.purchase.order.entity.QOrderInfo.orderInfo;
import static com.purchase.order.entity.QOrderPayment.orderPayment;

@Repository(value = "order-query")
public class OrderQueryGenerator {
    @PersistenceContext
    protected EntityManager entityManager;

    protected JPAQueryFactory jpaQueryFactory;

    public OrderQueryGenerator(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public List<OrderViewModel> selectOrderProductDetail(SelectOrderCommand selectOrderCommand) {
        var result = this.jpaQueryFactory.from(orderPayment)
            .innerJoin(orderInfo).on(orderPayment.orderPaymentSeq.eq(orderInfo.orderPaymentSeq.orderPaymentSeq))
            .where(orderInfo.product.productSeq.eq(selectOrderCommand.getProductSeq())
                .and(orderInfo.member.memberSeq.eq(selectOrderCommand.getMemberSeq())))
            .select(Projections.fields(OrderViewModel.class,
                orderInfo.product.productSeq,
                orderInfo.product.productName,
                orderInfo.product.productPrice,
                orderInfo.product.productDescription,
                orderInfo.product.createDate.as("productRegisterDate"),
                orderInfo.product.updateDate.as("productModifiedDate"),
                orderInfo.product.category.categorySeq,
                orderInfo.orderDate,
                orderInfo.paymentPrice,
                orderInfo.productCount))
            .fetch();
        return result;
    }

    public List<OrderViewModel> selectOrderList(SelectOrderCommand selectOrderCommand) {
        var result = this.jpaQueryFactory.from(orderPayment)
            .innerJoin(orderInfo).on(orderPayment.orderPaymentSeq.eq(orderInfo.orderPaymentSeq.orderPaymentSeq))
            .where(orderInfo.member.memberSeq.eq(selectOrderCommand.getMemberSeq()))
            .select(Projections.fields(OrderViewModel.class,
                orderInfo.product.productSeq,
                orderInfo.product.productName,
                orderInfo.product.productPrice,
                orderInfo.product.productDescription,
                orderInfo.product.createDate.as("productRegisterDate"),
                orderInfo.product.updateDate.as("productModifiedDate"),
                orderInfo.product.category.categorySeq,
                orderInfo.orderDate,
                orderInfo.paymentPrice,
                orderInfo.productCount))
            .fetch();
        return result;
    }
}
