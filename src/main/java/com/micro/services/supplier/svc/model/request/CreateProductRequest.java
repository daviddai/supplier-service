package com.micro.services.supplier.svc.model.request;

import java.util.Optional;

public class CreateProductRequest extends ProductRequest {

    public CreateProductRequest(String productName, Optional<String> productDescription, boolean publish) {
        super(productName, productDescription, publish);
    }
}
