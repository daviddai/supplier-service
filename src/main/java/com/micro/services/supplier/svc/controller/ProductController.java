package com.micro.services.supplier.svc.controller;

import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import com.micro.services.supplier.svc.service.EventService;
import com.micro.services.supplier.svc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/create")
    public ProductApiModel create(@RequestBody CreateProductRequest request) {
        ProductApiModel productApiModel =  productService.createProduct(request);
        eventService.publish(productApiModel.getProductCode());
        return productApiModel;
    }

    @GetMapping("/{productCode}")
    public ProductApiModel get(@PathVariable("productCode") String productCode) {
        return null;
    }

}
