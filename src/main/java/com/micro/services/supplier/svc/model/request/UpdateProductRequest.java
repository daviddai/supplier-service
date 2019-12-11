package com.micro.services.supplier.svc.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateProductRequest {

    private UpdateProductDetailRequest updateProductDetailRequest;
    private UpdateProductAvailabilityRequest updateProductAvailabilityRequest;

    @JsonCreator
    public UpdateProductRequest(@JsonProperty("updateProductContentRequest") UpdateProductDetailRequest updateProductDetailRequest,
                                @JsonProperty("updateProductAvailabilityRequest") UpdateProductAvailabilityRequest updateProductAvailabilityRequest) {
        this.updateProductDetailRequest = updateProductDetailRequest;
        this.updateProductAvailabilityRequest = updateProductAvailabilityRequest;
    }

    public UpdateProductDetailRequest getUpdateProductDetailRequest() {
        return updateProductDetailRequest;
    }

    public UpdateProductAvailabilityRequest getUpdateProductAvailabilityRequest() {
        return updateProductAvailabilityRequest;
    }
}
