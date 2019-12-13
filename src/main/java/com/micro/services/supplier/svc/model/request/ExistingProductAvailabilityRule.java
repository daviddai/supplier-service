package com.micro.services.supplier.svc.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ExistingProductAvailabilityRule extends ProductAvailabilityRule {

    private long id;

    @JsonCreator
    public ExistingProductAvailabilityRule(@JsonProperty("startDate") LocalDate startDate,
                                           @JsonProperty("endDate") LocalDate endDate,
                                           @JsonProperty("id") long id) {
        super(startDate, endDate);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
