package com.micro.services.supplier.svc.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductApiModel {

    private ProductContentApiModel productContentApiModel;
    private ProductAvailabilityApiModel productAvailabilityApiModel;

    @JsonCreator
    public ProductApiModel(@JsonProperty("productContentApiModel") ProductContentApiModel productContentApiModel,
                           @JsonProperty("productAvailabilityApiModel") ProductAvailabilityApiModel productAvailabilityApiModel) {
        this.productContentApiModel = productContentApiModel;
        this.productAvailabilityApiModel = productAvailabilityApiModel;
    }

    public ProductContentApiModel getProductContentApiModel() {
        return productContentApiModel;
    }

    public ProductAvailabilityApiModel getProductAvailabilityApiModel() {
        return productAvailabilityApiModel;
    }
}
