package com.micro.services.supplier.svc.model.request;

import com.micro.services.event.bus.event.model.ProductAvailabilityRule;

import java.util.List;

public class UpdateProductAvailabilityRequest extends ProductRequest {

    private String productCode;
    private List<ProductAvailabilityRule> productAvailabilityRules;
    private List<ProductAvailabilityRule> productUnavailabilityRules;

    public UpdateProductAvailabilityRequest(String productCode,
                                            List<ProductAvailabilityRule> productAvailabilityRules,
                                            List<ProductAvailabilityRule> productUnavailabilityRules,
                                            boolean publish) {
        super(publish);
        this.productCode = productCode;
        this.productAvailabilityRules = productAvailabilityRules;
        this.productUnavailabilityRules = productUnavailabilityRules;
    }

    public String getProductCode() {
        return productCode;
    }

    public List<ProductAvailabilityRule> getProductAvailabilityRules() {
        return productAvailabilityRules;
    }

    public List<ProductAvailabilityRule> getProductUnAvailabilities() {
        return productUnavailabilityRules;
    }
}
