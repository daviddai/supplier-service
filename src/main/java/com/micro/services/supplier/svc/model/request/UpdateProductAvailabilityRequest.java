package com.micro.services.supplier.svc.model.request;

import java.util.List;

public class UpdateProductAvailabilityRequest extends ProductRequest {

    private String productCode;
    private List<ProductAvailabilityRule> productAvailabilityRules;

    public UpdateProductAvailabilityRequest(String productCode,
                                            List<ProductAvailabilityRule> productAvailabilityRules,
                                            boolean publish) {
        super(publish);
        this.productCode = productCode;
        this.productAvailabilityRules = productAvailabilityRules;
    }

    public String getProductCode() {
        return productCode;
    }

    public List<ProductAvailabilityRule> getProductAvailabilityRules() {
        return productAvailabilityRules;
    }

}
