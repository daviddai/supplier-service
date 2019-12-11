package com.micro.services.supplier.svc.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ProductAvailabilityRule {

    private LocalDate startDate;
    private LocalDate endDate;

    @JsonCreator
    public ProductAvailabilityRule(@JsonProperty("startDate") LocalDate startDate,
                                   @JsonProperty("endDate") LocalDate endDate) {
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
