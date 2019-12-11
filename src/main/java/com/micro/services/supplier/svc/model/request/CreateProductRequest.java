package com.micro.services.supplier.svc.model.request;

import java.util.List;

public class CreateProductRequest extends ProductRequest {

    private String productName;
    private String productDescription;
    private List<ProductAvailabilityRule> productAvailabilityRules;

    public CreateProductRequest(String productName,
                                String productDescription,
                                List<ProductAvailabilityRule> productAvailabilityRules,
                                boolean publish) {
        super(publish);
        this.productName = productName;
        this.productDescription = productDescription;
        this.productAvailabilityRules = productAvailabilityRules;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public List<ProductAvailabilityRule> getProductAvailabilityRules() {
        return productAvailabilityRules;
    }

    public List<ProductAvailabilityRule> getProductAvailableDateRanges() {
        return productAvailabilityRules;
    }
}
