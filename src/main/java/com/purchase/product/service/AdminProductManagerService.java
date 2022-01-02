package com.purchase.product.service;

import com.purchase.category.repository.CategoryRepository;
import com.purchase.product.command.RegisterProductCommand;
import com.purchase.product.command.UpdateProductCommand;
import com.purchase.product.entity.Product;
import com.purchase.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminProductManagerService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AdminProductManagerService(ProductRepository productRepository,
                                      CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Product registerProduct(RegisterProductCommand registerProductCommand) {
        return this.productRepository.save(registerProductCommand.toEntity(categoryRepository));
    }

    @Transactional
    public Optional<Product> updateProduct(UpdateProductCommand updateProductCommand) {
        var target = this.productRepository.findById(updateProductCommand.getProductSeq());
        var category = this.categoryRepository.findById(updateProductCommand.getCategorySeq());
        target.ifPresent(product -> product.update(updateProductCommand, category.get()));
        return target;
    }
}
