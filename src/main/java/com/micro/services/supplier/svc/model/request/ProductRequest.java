package com.micro.services.supplier.svc.model.request;

import java.util.Optional;

public abstract class ProductRequest {

    private String productName;
    private Optional<String> productDescription;
    private boolean publishEvent;

    public ProductRequest(String productName, Optional<String> productDescription, boolean publishEvent) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.publishEvent = publishEvent;
    }

    public String getProductName() {
        return productName;
    }

    public Optional<String> getProductDescription() {
        return productDescription;
    }

    public boolean isPublishEvent() {
        return publishEvent;
    }
}
