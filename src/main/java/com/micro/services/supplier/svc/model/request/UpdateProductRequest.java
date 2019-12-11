package com.micro.services.supplier.svc.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateProductRequest {

    private UpdateProductContentRequest updateProductContentRequest;
    private UpdateProductAvailabilityRequest updateProductAvailabilityRequest;

    @JsonCreator
    public UpdateProductRequest(@JsonProperty("updateProductContentRequest") UpdateProductContentRequest updateProductContentRequest,
                                @JsonProperty("updateProductAvailabilityRequest") UpdateProductAvailabilityRequest updateProductAvailabilityRequest) {
        this.updateProductContentRequest = updateProductContentRequest;
        this.updateProductAvailabilityRequest = updateProductAvailabilityRequest;
    }

    public UpdateProductContentRequest getUpdateProductContentRequest() {
        return updateProductContentRequest;
    }

    public UpdateProductAvailabilityRequest getUpdateProductAvailabilityRequest() {
        return updateProductAvailabilityRequest;
    }
}
