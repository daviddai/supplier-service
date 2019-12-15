package com.micro.services.supplier.svc.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.services.supplier.svc.model.AvailabilityRule;

import java.time.LocalDate;

public class ExistingNewProductAvailabilityRule extends AvailabilityRule {

    private long id;

    @JsonCreator
    public ExistingNewProductAvailabilityRule(@JsonProperty("startDate") LocalDate startDate,
                                              @JsonProperty("endDate") LocalDate endDate,
                                              @JsonProperty("id") long id) {
        super(startDate, endDate);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
