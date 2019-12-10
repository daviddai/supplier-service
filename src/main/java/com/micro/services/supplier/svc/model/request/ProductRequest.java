package com.micro.services.supplier.svc.model.request;

import com.micro.services.event.bus.event.model.ProductAvailabilityRule;

import java.util.List;
import java.util.Optional;

public abstract class ProductRequest {

    private String productName;
    private Optional<String> productDescription;
    private List<ProductAvailabilityRule> productAvailabilityRules;
    private boolean publish;

    public ProductRequest(String productName,
                          Optional<String> productDescription,
                          List<ProductAvailabilityRule> productAvailabilityRules,
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

    public List<ProductAvailabilityRule> getProductAvailabilityRules() {
        return productAvailabilityRules;
    }

    public boolean isPublish() {
        return publish;
    }
}
