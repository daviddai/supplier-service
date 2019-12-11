package com.micro.services.supplier.svc.service;

import com.micro.services.supplier.svc.dao.ProductAvailabilityRuleDao;
import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDTO;
import com.micro.services.supplier.svc.model.request.ProductAvailabilityRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAvailabilityRuleService {

    @Autowired
    private ProductAvailabilityRuleDao productAvailabilityRuleDao;

    public List<ProductAvailabilityRuleDTO> addAvailabilityRules(String productCode,
                                                                 List<ProductAvailabilityRule> productAvailabilityRules) {

        List<ProductAvailabilityRuleDTO> productAvailabilityRuleDTOs = productAvailabilityRules.stream()
                .map(productAvailabilityRule -> constructProductAvailabilityDTO(productCode, productAvailabilityRule))
                .collect(Collectors.toList());

        productAvailabilityRuleDTOs.forEach(productAvailabilityRuleDao::save);

        return productAvailabilityRuleDTOs;
    }

    private ProductAvailabilityRuleDTO constructProductAvailabilityDTO(String productCode,
                                                                       ProductAvailabilityRule productAvailabilityRule) {

        return new ProductAvailabilityRuleDTO(productCode,
                productAvailabilityRule.getStartDate(), productAvailabilityRule.getEndDate());
    }

}
