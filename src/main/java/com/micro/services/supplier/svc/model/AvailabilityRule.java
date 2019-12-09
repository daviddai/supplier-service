package com.micro.services.supplier.svc.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class AvailabilityRule {
    private Date startDate;
    private Date endDate;

    @JsonCreator
    public AvailabilityRule(@JsonProperty("startDate") Date startDate, @JsonProperty("endDate") Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}