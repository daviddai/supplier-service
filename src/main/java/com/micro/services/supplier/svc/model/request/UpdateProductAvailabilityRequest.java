package com.micro.services.supplier.svc.model.request;

import java.util.List;

public class UpdateProductAvailabilityRequest extends ProductRequest {

    private String productCode;
    private List<NewProductAvailabilityRule> newProductAvailabilityRules;
    private List<ExistingNewProductAvailabilityRule> updatedProductAvailabilityRules;
    private List<Long> removedProductAvailabilityRuleIds;


    public UpdateProductAvailabilityRequest(boolean publish,
                                            String productCode,
                                            List<NewProductAvailabilityRule> newProductAvailabilityRules,
                                            List<ExistingNewProductAvailabilityRule> updatedProductAvailabilityRules,
                                            List<Long> removedProductAvailabilityRuleIds) {
        super(publish);
        this.productCode = productCode;
        this.newProductAvailabilityRules = newProductAvailabilityRules;
        this.updatedProductAvailabilityRules = updatedProductAvailabilityRules;
        this.removedProductAvailabilityRuleIds = removedProductAvailabilityRuleIds;
    }

    public String getProductCode() {
        return productCode;
    }

    public List<NewProductAvailabilityRule> getNewProductAvailabilityRules() {
        return newProductAvailabilityRules;
    }

    public List<ExistingNewProductAvailabilityRule> getUpdatedProductAvailabilityRules() {
        return updatedProductAvailabilityRules;
    }

    public List<Long> getRemovedProductAvailabilityRuleIds() {
        return removedProductAvailabilityRuleIds;
    }
}
