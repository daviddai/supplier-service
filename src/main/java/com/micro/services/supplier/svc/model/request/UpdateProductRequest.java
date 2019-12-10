package com.micro.services.supplier.svc.model.request;

import com.micro.services.event.bus.event.model.ProductAvailabilityRule;

import java.util.List;
import java.util.Optional;

public class UpdateProductRequest extends ProductRequest {

    private List<ProductAvailabilityRule> productUnavailabilityRules;

    public UpdateProductRequest(String productName,
                                Optional<String> productDescription,
                                List<ProductAvailabilityRule> productAvailabilityRules,
                                List<ProductAvailabilityRule> productUnavailabilityRules,
                                boolean publishEvent) {
        super(productName, productDescription, productAvailabilityRules, publishEvent);
        this.productUnavailabilityRules = productUnavailabilityRules;
    }

    public List<ProductAvailabilityRule> getProductUnAvailabilities() {
        return productUnavailabilityRules;
    }
}
