package com.micro.services.supplier.svc.dao.model;

import java.time.LocalDate;

public class ProductAvailabilityRuleDTO {
    private long id;
    private String productCode;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProductAvailabilityRuleDTO(long id, String productCode, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.productCode = productCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
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
