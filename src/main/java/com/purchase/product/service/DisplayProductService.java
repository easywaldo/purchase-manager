package com.purchase.product.service;

import com.purchase.product.dto.ProductViewModel;
import com.purchase.product.repository.ProductRepository;
import com.purchase.querygenerator.ProductQueryGenerator;
import com.purchase.querygenerator.command.SearchProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayProductService {
    private final ProductRepository productRepository;
    private final ProductQueryGenerator productQueryGenerator;

    @Autowired
    public DisplayProductService(ProductRepository productRepository,
                                 ProductQueryGenerator productQueryGenerator) {
        this.productRepository = productRepository;
        this.productQueryGenerator = productQueryGenerator;
    }

    public List<ProductViewModel> selectProductList(SearchProductCommand searchCommand) {
        return this.productQueryGenerator.selectProductList(searchCommand);
    }
}
