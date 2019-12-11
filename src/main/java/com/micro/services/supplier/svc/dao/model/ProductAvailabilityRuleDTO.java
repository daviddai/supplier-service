package com.micro.services.supplier.svc.dao.model;

import java.time.LocalDate;

public class ProductAvailabilityRuleDTO {

    private String productCode;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProductAvailabilityRuleDTO(String productCode, LocalDate startDate, LocalDate endDate) {
        this.productCode = productCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
