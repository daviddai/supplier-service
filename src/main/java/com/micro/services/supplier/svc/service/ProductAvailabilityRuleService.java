package com.micro.services.supplier.svc.service;

import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDto;
import com.micro.services.supplier.svc.model.request.ProductAvailabilityRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAvailabilityRuleService {

    public List<ProductAvailabilityRuleDto> addAvailabilityRules(String productCode,
                                                                 List<ProductAvailabilityRule> productAvailabilityRules) {
        return null;
    }

}
