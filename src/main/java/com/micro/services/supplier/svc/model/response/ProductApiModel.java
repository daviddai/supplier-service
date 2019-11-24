package com.micro.services.supplier.svc.model.response;

import java.util.Optional;

public class ProductApiModel {

    private String productCode;
    private String productName;
    private Optional<String> productDescription;

    public ProductApiModel(String productCode, String productName, Optional<String> productDescription) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public Optional<String> getProductDescription() {
        return productDescription;
    }
}
