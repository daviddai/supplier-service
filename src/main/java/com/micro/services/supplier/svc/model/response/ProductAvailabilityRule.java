package com.micro.services.supplier.svc.model.response;

import com.micro.services.supplier.svc.model.AvailabilityRule;

import java.time.LocalDate;

public class ProductAvailabilityRule extends AvailabilityRule {

    private long id;
    private String productCode;

    public ProductAvailabilityRule(LocalDate startDate, LocalDate endDate, long id, String productCode) {
        super(startDate, endDate);
        this.id = id;
        this.productCode = productCode;
    }

    public long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }
}
