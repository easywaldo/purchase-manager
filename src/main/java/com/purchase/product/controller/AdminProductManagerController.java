package com.purchase.product.controller;

import com.purchase.category.repository.CategoryRepository;
import com.purchase.product.command.RegisterProductCommand;
import com.purchase.product.command.UpdateProductCommand;
import com.purchase.product.dto.AdminProductViewModel;
import com.purchase.product.dto.ProductViewModel;
import com.purchase.product.service.AdminProductManagerService;
import com.purchase.product.service.DisplayProductService;
import com.purchase.querygenerator.command.SearchProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class AdminProductManagerController {
    private final AdminProductManagerService adminProductManagerService;
    private final CategoryRepository categoryRepository;
    private final DisplayProductService displayProductService;

    @Autowired
    public AdminProductManagerController(AdminProductManagerService adminProductManagerService,
                                         DisplayProductService displayProductService,
                                         CategoryRepository categoryRepository) {
        this.adminProductManagerService = adminProductManagerService;
        this.displayProductService = displayProductService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(value = "/admin/register/product")
    public Mono<ResponseEntity<?>> registerProduct(@RequestBody RegisterProductCommand registerProductCommand) {
        var result = this.adminProductManagerService.registerProduct(registerProductCommand);
        var response = AdminProductViewModel.builder()
            .productName(result.getProductName())
            .productSeq(result.getProductSeq())
            .categorySeq(result.getCategorySeq().getCategorySeq())
            .categoryName(categoryRepository.findById(result.getCategorySeq().getCategorySeq()).get().getCategoryName())
            .productDescription(result.getProductDescription())
            .createDt(result.getCreateDate())
            .updateDt(result.getUpdateDate())
            .build();
        return Mono.just(ResponseEntity.ok().body(response));
    }

    @PostMapping(value = "/admin/update/product")
    public Mono<ResponseEntity<?>> updateProduct(@RequestBody UpdateProductCommand updateProductCommand) {
        var result = this.adminProductManagerService.updateProduct(updateProductCommand);
        if (result.isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body(result));
        }
        var updated = result.get();
        var response = AdminProductViewModel.builder()
            .productName(updated.getProductName())
            .productSeq(updated.getProductSeq())
            .categorySeq(updated.getCategorySeq().getCategorySeq())
            .categoryName(categoryRepository.findById(updated.getCategorySeq().getCategorySeq()).get().getCategoryName())
            .productDescription(updated.getProductDescription())
            .createDt(updated.getCreateDate())
            .updateDt(updated.getUpdateDate())
            .build();
        return Mono.just(ResponseEntity.ok().body(response));
    }

    @PostMapping(value = "/admin/list/product/")
    public List<ProductViewModel> displayProductList(@RequestBody SearchProductCommand searchProductCommand) {
        return this.displayProductService.selectProductList(searchProductCommand);
    }
}
