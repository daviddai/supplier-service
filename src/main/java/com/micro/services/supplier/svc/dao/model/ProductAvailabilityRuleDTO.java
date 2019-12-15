package com.micro.services.supplier.svc.dao.model;

import java.sql.Date;

public class ProductAvailabilityRuleDTO {
    private long id;
    private String productCode;
    private Date startDate;
    private Date endDate;

    public ProductAvailabilityRuleDTO(long id, String productCode, Date startDate, Date endDate) {
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
