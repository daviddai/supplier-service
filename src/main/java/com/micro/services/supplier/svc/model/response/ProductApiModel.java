package com.micro.services.supplier.svc.model.response;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductApiModel {

    private String productCode;
    private String productName;
    private Optional<String> productDescription;
    private List<Date> productAvailabilities;

    public ProductApiModel(String productCode,
                           String productName,
                           Optional<String> productDescription,
                           List<Date> productAvailabilities) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productAvailabilities = productAvailabilities;
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

    public List<Date> getProductAvailabilities() {
        return productAvailabilities;
    }
}
