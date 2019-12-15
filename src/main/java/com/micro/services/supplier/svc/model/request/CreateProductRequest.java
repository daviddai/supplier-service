package com.micro.services.supplier.svc.model.request;

import java.util.List;

public class CreateProductRequest extends ProductRequest {

    private String productName;
    private String productDescription;
    private List<NewProductAvailabilityRule> newProductAvailabilityRules;

    public CreateProductRequest(String productName,
                                String productDescription,
                                List<NewProductAvailabilityRule> newProductAvailabilityRules,
                                boolean publish) {
        super(publish);
        this.productName = productName;
        this.productDescription = productDescription;
        this.newProductAvailabilityRules = newProductAvailabilityRules;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public List<NewProductAvailabilityRule> getNewProductAvailabilityRules() {
        return newProductAvailabilityRules;
    }

    public List<NewProductAvailabilityRule> getProductAvailableDateRanges() {
        return newProductAvailabilityRules;
    }
}
