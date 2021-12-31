package com.purchase.category.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_seq")
    private Integer categorySeq;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_depth")
    private Integer categoryDepth;

    @Column(name = "category_large_code")
    private String categoryLargeCode;

    @Column(name = "category_medium_code")
    private String categoryMediumCode;

    @Builder
    public Category(Integer categorySeq,
                    String categoryName,
                    Integer categoryDepth,
                    String categoryLargeCode,
                    String categoryMediumCode) {
        this.categorySeq = categorySeq;
        this.categoryName = categoryName;
        this.categoryDepth = categoryDepth;
        this.categoryLargeCode = categoryLargeCode;
        this.categoryMediumCode = categoryMediumCode;
    }
}
