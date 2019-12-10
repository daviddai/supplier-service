package com.micro.services.supplier.svc.model.request;

import com.micro.services.event.bus.event.model.ProductAvailabilityRule;

import java.util.List;
import java.util.Optional;

public class CreateProductRequest extends ProductRequest {

    public CreateProductRequest(String productName,
                                Optional<String> productDescription,
                                List<ProductAvailabilityRule> productAvailabilities,
                                boolean publish) {
        super(productName, productDescription, productAvailabilities, publish);
    }
}
