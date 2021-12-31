package com.purchase.product.entity;

import com.purchase.category.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_seq")
    private Integer productSeq;

    @ManyToOne
    @JoinColumn(name = "category_seq", referencedColumnName = "category_seq")
    private Category categorySeq;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private Integer productPrice;

    @CreationTimestamp
    @Column(name = "create_dt")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "update_dt")
    private LocalDateTime updateDate;

    @Builder
    public Product(Integer productSeq,
                   Category categorySeq,
                   String productName,
                   String productDescription,
                   Integer productPrice,
                   LocalDateTime createDate,
                   LocalDateTime updateDate) {
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
