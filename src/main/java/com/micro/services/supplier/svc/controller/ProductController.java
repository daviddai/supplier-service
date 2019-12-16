package com.micro.services.supplier.svc.controller;

import com.micro.services.supplier.svc.exception.SupplierServiceException;
import com.micro.services.supplier.svc.facade.ProductFacade;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductAvailabilityRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductDetailRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import com.micro.services.supplier.svc.model.response.ProductAvailabilityRuleApiModel;
import com.micro.services.supplier.svc.model.response.ProductDetailApiModel;
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

    @GetMapping("/detail/{productCode}")
    public ProductDetailApiModel getContent(@PathVariable("productCode") String productCode) throws SupplierServiceException {
        return productFacade.getProductDetail(productCode);
    }

    @GetMapping("/availability-rule/{productCode}")
    public ProductAvailabilityRuleApiModel getAvailabilityRules(@PathVariable("productCode") String productCode) {
        return productFacade.getProductAvailabilityRules(productCode);
    }

    @PostMapping("/update")
    public ProductApiModel update(@RequestBody UpdateProductRequest request) {
        return null;
    }

    @PostMapping("/update/content")
    public ProductDetailApiModel updateContent(@RequestBody UpdateProductDetailRequest request) {
        return productFacade.updateProductDetail(request);
    }

    @PostMapping("update/availability")
    public ProductAvailabilityRuleApiModel updateAvailability(@RequestBody UpdateProductAvailabilityRequest request) {
        return productFacade.updateProductAvailability(request);
    }

    @PostMapping(value = "/publish/content/{productCode}")
    public void publishContent(@PathVariable("productCode") String productCode) throws SupplierServiceException {
        productFacade.publishProductContent(productCode);
    }

    @PostMapping(value = "/publish/availability/{productCode}")
    public void publishAvailability(@PathVariable("productCode") String productCode) {
        productFacade.publishProductAvailablePeriods(productCode);
    }

}
