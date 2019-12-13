package com.micro.services.supplier.svc.model.request;

import java.util.List;

public class UpdateProductAvailabilityRequest extends ProductRequest {

    private String productCode;
    private List<ProductAvailabilityRule> newProductAvailabilityRules;
    private List<ExistingProductAvailabilityRule> updatedProductAvailabilityRules;
    private List<Long> removedProductAvailabilityRuleIds;


    public UpdateProductAvailabilityRequest(boolean publish,
                                            String productCode,
                                            List<ProductAvailabilityRule> newProductAvailabilityRules,
                                            List<ExistingProductAvailabilityRule> updatedProductAvailabilityRules,
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

    public List<ProductAvailabilityRule> getNewProductAvailabilityRules() {
        return newProductAvailabilityRules;
    }

    public List<ExistingProductAvailabilityRule> getUpdatedProductAvailabilityRules() {
        return updatedProductAvailabilityRules;
    }

    public List<Long> getRemovedProductAvailabilityRuleIds() {
        return removedProductAvailabilityRuleIds;
    }
}
