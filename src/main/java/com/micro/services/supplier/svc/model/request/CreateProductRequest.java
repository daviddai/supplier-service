package com.micro.services.supplier.svc.model.request;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CreateProductRequest extends ProductRequest {

    public CreateProductRequest(String productName,
                                Optional<String> productDescription,
                                List<Date> productAvailabilities,
                                boolean publish) {
        super(productName, productDescription, productAvailabilities, publish);
    }
}
