package com.micro.services.supplier.svc.model.request;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class ProductRequest {

    private String productName;
    private Optional<String> productDescription;
    private List<Date> productAvailabilities;
    private boolean publishEvent;

    public ProductRequest(String productName, Optional<String> productDescription, List<Date> productAvailabilities, boolean publishEvent) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productAvailabilities = productAvailabilities;
        this.publishEvent = publishEvent;
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

    public boolean isPublishEvent() {
        return publishEvent;
    }
}
