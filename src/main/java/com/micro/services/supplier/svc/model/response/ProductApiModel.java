package com.micro.services.supplier.svc.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductApiModel {

    private ProductDetailApiModel productDetailApiModel;
    private ProductAvailabilityRuleApiModel productAvailabilityRuleApiModel;

    @JsonCreator
    public ProductApiModel(@JsonProperty("productContentApiModel") ProductDetailApiModel productDetailApiModel,
                           @JsonProperty("productAvailabilityRuleApiModel") ProductAvailabilityRuleApiModel productAvailabilityRuleApiModel) {
        this.productDetailApiModel = productDetailApiModel;
        this.productAvailabilityRuleApiModel = productAvailabilityRuleApiModel;
    }

    public ProductDetailApiModel getProductDetailApiModel() {
        return productDetailApiModel;
    }

    public ProductAvailabilityRuleApiModel getProductAvailabilityRuleApiModel() {
        return productAvailabilityRuleApiModel;
    }
}
