package com.micro.services.supplier.svc.model.request;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.micro.services.supplier.svc.model.AvailabilityRule;

public abstract class ProductRequest {

    private String productName;
    private Optional<String> productDescription;
    private List<AvailabilityRule> productAvailabilityRules;
    private boolean publish;

    public ProductRequest(String productName,
                          Optional<String> productDescription,
                          List<AvailabilityRule> productAvailabilityRules,
                          boolean publish) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productAvailabilityRules = productAvailabilityRules;
        this.publish = publish;
    }

    public String getProductName() {
        return productName;
    }

    public Optional<String> getProductDescription() {
        return productDescription;
    }

    public List<AvailabilityRule> getProductAvailabilityRules() {
        return productAvailabilityRules;
    }

    public boolean isPublish() {
        return publish;
    }
}
