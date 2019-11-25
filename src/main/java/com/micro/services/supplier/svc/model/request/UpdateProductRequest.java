package com.micro.services.supplier.svc.model.request;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UpdateProductRequest extends ProductRequest {

    private List<Date> productUnAvailabilities;

    public UpdateProductRequest(String productName,
                                Optional<String> productDescription,
                                List<Date> productAvailabilities,
                                List<Date> productUnAvailabilities,
                                boolean publishEvent) {
        super(productName, productDescription, productAvailabilities, publishEvent);
        this.productUnAvailabilities = productUnAvailabilities;
    }

    public List<Date> getProductUnAvailabilities() {
        return productUnAvailabilities;
    }
}
