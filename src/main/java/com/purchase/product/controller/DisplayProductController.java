package com.purchase.product.controller;

import com.purchase.product.dto.ProductViewModel;
import com.purchase.product.service.DisplayProductService;
import com.purchase.querygenerator.command.SearchProductCommand;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DisplayProductController {
    private DisplayProductService displayProductService;

    @Autowired
    public DisplayProductController(DisplayProductService displayProductService) {
        this.displayProductService = displayProductService;
    }

    @ApiOperation(value = "상품전시서비스", notes = "상품에 대한 리스트를 조회한다.")
    @PostMapping(value = "/display/product/")
    public List<ProductViewModel> displayProductList(@RequestBody SearchProductCommand searchProductCommand) {
        return this.displayProductService.selectProductList(searchProductCommand);
    }
}
