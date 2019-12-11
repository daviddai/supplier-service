package com.micro.services.supplier.svc.model.response;

public class ProductDetailApiModel {

    private String productCode;
    private String productName;
    private String productDescription;

    public ProductDetailApiModel(String productCode,
                                 String productName,
                                 String productDescription) {
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
