package com.micro.services.supplier.svc.model.request;

import java.util.List;
import java.util.Optional;

import com.micro.services.supplier.svc.model.AvailabilityRule;

public class CreateProductRequest extends ProductRequest {

    public CreateProductRequest(String productName,
                                Optional<String> productDescription,
                                List<AvailabilityRule> productAvailabilities,
                                boolean publish) {
        super(productName, productDescription, productAvailabilities, publish);
    }
}
