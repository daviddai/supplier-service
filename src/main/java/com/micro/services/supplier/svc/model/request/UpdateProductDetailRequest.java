package com.micro.services.supplier.svc.model.request;

public class UpdateProductDetailRequest extends ProductRequest {

    private String productCode;
    private String productName;
    private String productDescription;

    public UpdateProductDetailRequest(String productCode,
                                      String productName,
                                      String productDescription,
                                      boolean publish) {
        super(publish);
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
