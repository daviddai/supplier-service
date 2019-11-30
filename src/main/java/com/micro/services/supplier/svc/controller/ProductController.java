package com.micro.services.supplier.svc.controller;

import com.micro.services.supplier.svc.facade.ProductFacade;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @PostMapping(value = "/create")
    public ProductApiModel create(@RequestBody CreateProductRequest request) {
        ProductApiModel productApiModel =  productFacade.createProduct(request);
        return productApiModel;
    }

    @GetMapping("/{productCode}")
    public ProductApiModel get(@PathVariable("productCode") String productCode) {
        return null;
    }

}
