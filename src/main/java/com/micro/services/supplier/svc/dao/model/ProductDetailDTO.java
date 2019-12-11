package com.micro.services.supplier.svc.dao.model;

public class ProductDetailDTO {

    private String productCode;
    private String productName;
    private String productDescription;

    public ProductDetailDTO(String productCode, String productName, String productDescription) {
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
