package com.micro.services.supplier.svc.model.request;

import com.micro.services.supplier.svc.model.AvailabilityRule;

import java.time.LocalDate;

public class NewProductAvailabilityRule extends AvailabilityRule {

    public NewProductAvailabilityRule(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

}
