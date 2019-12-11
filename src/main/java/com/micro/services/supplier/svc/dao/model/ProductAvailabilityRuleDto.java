package com.micro.services.supplier.svc.dao.model;

import java.time.LocalDate;

public class ProductAvailabilityRuleDto {

    private LocalDate startDate;
    private LocalDate endDate;

    public ProductAvailabilityRuleDto(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
