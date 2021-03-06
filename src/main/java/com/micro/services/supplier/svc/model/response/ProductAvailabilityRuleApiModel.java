package com.micro.services.supplier.svc.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDTO;

import java.util.List;

public class ProductAvailabilityRuleApiModel {

    private String productCode;
    private List<ProductAvailabilityRule> productAvailabilityRules;

    @JsonCreator
    public ProductAvailabilityRuleApiModel(@JsonProperty("productCode") String productCode,
                                           @JsonProperty("productAvailabilityRules") List<ProductAvailabilityRule> productAvailabilityRules) {
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
