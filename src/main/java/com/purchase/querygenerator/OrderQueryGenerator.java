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
            .where(orderInfo.productSeq.productSeq.eq(selectOrderCommand.getProductSeq())
                .and(orderInfo.memberSeq.memberSeq.eq(selectOrderCommand.getMemberSeq())))
            .select(Projections.fields(OrderViewModel.class,
                orderInfo.productSeq.productSeq,
                orderInfo.productSeq.productName,
                orderInfo.productSeq.productPrice,
                orderInfo.productSeq.productDescription,
                orderInfo.productSeq.createDate.as("productRegisterDate"),
                orderInfo.productSeq.updateDate.as("productModifiedDate"),
                orderInfo.productSeq.categorySeq.categorySeq,
                orderInfo.orderDate,
                orderInfo.paymentPrice,
                orderInfo.productCount))
            .fetch();
        return result;
    }

    public List<OrderViewModel> selectOrderList(SelectOrderCommand selectOrderCommand) {
        var result = this.jpaQueryFactory.from(orderPayment)
            .innerJoin(orderInfo).on(orderPayment.orderPaymentSeq.eq(orderInfo.orderPaymentSeq.orderPaymentSeq))
            .where(orderInfo.memberSeq.memberSeq.eq(selectOrderCommand.getMemberSeq()))
            .select(Projections.fields(OrderViewModel.class,
                orderInfo.productSeq.productSeq,
                orderInfo.productSeq.productName,
                orderInfo.productSeq.productPrice,
                orderInfo.productSeq.productDescription,
                orderInfo.productSeq.createDate.as("productRegisterDate"),
                orderInfo.productSeq.updateDate.as("productModifiedDate"),
                orderInfo.productSeq.categorySeq.categorySeq,
                orderInfo.orderDate,
                orderInfo.paymentPrice,
                orderInfo.productCount))
            .fetch();
        return result;
    }
}
