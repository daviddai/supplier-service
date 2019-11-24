package com.micro.services.supplier.svc.model.request;

import java.util.Optional;

public class UpdateProductRequest extends ProductRequest {

    public UpdateProductRequest(String productName, Optional<String> productDescription, boolean publish) {
        super(productName, productDescription, publish);
    }
}
