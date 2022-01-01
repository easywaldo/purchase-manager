package com.purchase.querygenerator;

import com.google.common.base.Strings;
import com.purchase.product.dto.ProductViewModel;
import com.purchase.querygenerator.command.SearchProductCommand;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.purchase.category.entity.QCategory.category;
import static com.purchase.product.entity.QProduct.product;

@Repository(value = "product-query")
public class ProductQueryGenerator {
    @PersistenceContext
    protected EntityManager entityManager;

    protected JPAQueryFactory jpaQueryFactory;

    public ProductQueryGenerator(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public List<ProductViewModel> selectProductList(SearchProductCommand searchCommand) {

        BooleanBuilder whereClause = new BooleanBuilder();
        if (!Strings.isNullOrEmpty(searchCommand.getCategoryLargeCode())) {
            whereClause.and(category.categoryLargeCode.eq(searchCommand.getCategoryLargeCode()));
        }
        if (!Strings.isNullOrEmpty(searchCommand.getCategoryMediumCode())) {
            whereClause.and(category.categoryMediumCode.eq(searchCommand.getCategoryMediumCode()));
        }
        if (searchCommand.getProductSeq() > 0) {
            whereClause.and(product.productSeq.eq(searchCommand.getProductSeq()));
        }
        if (!Strings.isNullOrEmpty(searchCommand.getCategoryName())) {
            whereClause.and(category.categoryName.contains(searchCommand.getCategoryName()));
        }
        var result = this.jpaQueryFactory.from(category)
            .join(product).on(category.categorySeq.eq(product.categorySeq.categorySeq))
            .where(whereClause)
            .select(Projections.fields(ProductViewModel.class,
                product.productName,
                product.productSeq,
                product.categorySeq.categorySeq,
                product.categorySeq.categoryName,
                product.productPrice))
            .fetch();
        return result;
    }
}
