package com.purchase.order.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderViewModel {
    private Integer productSeq;
    private Integer categorySeq;
    private String productName;
    private Integer productPrice;
    private String productDescription;
    private LocalDateTime orderDate;
    private LocalDateTime productRegisterDate;
    private LocalDateTime productModifiedDate;
}
