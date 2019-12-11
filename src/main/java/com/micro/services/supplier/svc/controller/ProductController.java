package com.micro.services.supplier.svc.controller;

import com.micro.services.supplier.svc.exception.SupplierServiceException;
import com.micro.services.supplier.svc.facade.ProductFacade;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductAvailabilityRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductContentRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import com.micro.services.supplier.svc.model.response.ProductAvailabilityApiModel;
import com.micro.services.supplier.svc.model.response.ProductContentApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @PostMapping(value = "/create")
    public ProductApiModel create(@RequestBody CreateProductRequest request) throws SupplierServiceException {
        return productFacade.createProduct(request);
    }

    @GetMapping("/{productCode}")
    public ProductContentApiModel get(@PathVariable("productCode") String productCode) throws SupplierServiceException {
        return productFacade.findProduct(productCode);
    }

    @PostMapping("/update")
    public ProductApiModel update(@RequestBody UpdateProductRequest request) {
        return null;
    }

    @PostMapping("/update/content")
    public ProductContentApiModel updateContent(@RequestBody UpdateProductContentRequest request) {
        return null;
    }

    @PostMapping("update/availability")
    public ProductAvailabilityApiModel updateAvailability(@RequestBody UpdateProductAvailabilityRequest request) {
        return null;
    }

    @PostMapping(value = "/publish/{productCode}")
    public void publish(@PathVariable("productCode") String productCode) throws SupplierServiceException {
        productFacade.publishExistingProduct(productCode);
    }

}
