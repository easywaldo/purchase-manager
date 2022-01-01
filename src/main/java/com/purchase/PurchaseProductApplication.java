package com.purchase;

import com.purchase.category.entity.Category;
import com.purchase.category.repository.CategoryRepository;
import com.purchase.member.entity.Member;
import com.purchase.member.repository.MemberRepository;
import com.purchase.order.entity.OrderInfo;
import com.purchase.order.entity.OrderPayment;
import com.purchase.order.repository.OrderInfoRepository;
import com.purchase.order.repository.OrderPaymentRepository;
import com.purchase.product.entity.Product;
import com.purchase.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class PurchaseProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseProductApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(
        MemberRepository memberRepository,
        OrderPaymentRepository orderPaymentRepository,
        OrderInfoRepository orderInfoRepository,
        CategoryRepository categoryRepository,
        ProductRepository productRepository) {
        return (args) -> {
            categoryRepository.deleteAll();
            categoryRepository.saveAll(
                List.of(
                    Category.builder()
                        .categoryLargeCode("001")
                        .categoryMediumCode("000")
                        .categoryName("홈 인테리어")
                        .categoryDepth(1)
                        .categorySeq(1)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("001")
                        .categoryMediumCode("000")
                        .categoryName("이벤트")
                        .categoryDepth(1)
                        .categorySeq(2)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("001")
                        .categoryMediumCode("000")
                        .categoryName("개발")
                        .categoryDepth(1)
                        .categorySeq(3)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("001")
                        .categoryMediumCode("001")
                        .categoryName("페인트시공")
                        .categoryDepth(2)
                        .categorySeq(4)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("001")
                        .categoryMediumCode("002")
                        .categoryName("집 인테리어")
                        .categoryDepth(2)
                        .categorySeq(5)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("001")
                        .categoryMediumCode("003")
                        .categoryName("싱크대 교체")
                        .categoryDepth(2)
                        .categorySeq(6)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("002")
                        .categoryMediumCode("001")
                        .categoryName("결혼식 사회자")
                        .categoryDepth(2)
                        .categorySeq(7)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("002")
                        .categoryMediumCode("002")
                        .categoryName("웨딩사진촬영")
                        .categoryDepth(2)
                        .categorySeq(8)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("003")
                        .categoryMediumCode("001")
                        .categoryName("앱 디자인")
                        .categoryDepth(2)
                        .categorySeq(9)
                        .build(),
                    Category.builder()
                        .categoryLargeCode("003")
                        .categoryMediumCode("002")
                        .categoryName("소프트웨어 개발")
                        .categoryDepth(2)
                        .categorySeq(10)
                        .build()));

            productRepository.saveAll(List.of(
                Product.builder()
                    .categorySeq(categoryRepository.findById(4).get())
                    .productSeq(1)
                    .productName("노루표페인트시공")
                    .productPrice(10000)
                    .productDescription("노루표페인트로 시공해드립니다.")
                    .build(),
                Product.builder()
                    .categorySeq(categoryRepository.findById(10).get())
                    .productSeq(2)
                    .productName("Java 웹서비스 구축")
                    .productPrice(20000)
                    .productDescription("자바웹서비스를 만들어드립니다.")
                    .build()
            ));

            memberRepository.saveAll(List.of(
                Member.builder()
                    .memberSeq(1)
                    .memberId("tester-001")
                    .memberPassword("tester001")
                    .build(),
                Member.builder()
                    .memberSeq(2)
                    .memberId("tester-002")
                    .memberPassword("tester002")
                    .build())
            );

            var orderPayment = orderPaymentRepository.save(OrderPayment.builder()
                .txTid("nicepay-tx-20220101010210")
                .orderId(UUID.randomUUID())
                .memberSeq(1)
                .orderPaymentSeq(1)
                .build());

            orderInfoRepository.saveAll(List.of(
                OrderInfo.builder()
                    .memberSeq(memberRepository.findById(1).get())
                    .paymentPrice(productRepository.findById(1).get().getProductPrice())
                    .orderDate(LocalDateTime.now())
                    .productSeq(productRepository.findById(1).get())
                    .orderPaymentSeq(orderPayment)
                    .orderInfoSeq(1)
                    .build()
            ));

        };
    }
}
