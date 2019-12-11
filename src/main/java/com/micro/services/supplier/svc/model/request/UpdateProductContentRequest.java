package com.micro.services.supplier.svc.model.request;

public class UpdateProductContentRequest extends CreateProductRequest {

    private String productCode;

    public UpdateProductContentRequest(String productName, String productDescription, boolean publish, String productCode) {
        super(productName, productDescription, publish);
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }
}
