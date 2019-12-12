package com.micro.services.supplier.svc.service;

import com.micro.services.supplier.svc.dao.ProductAvailabilityRuleDao;
import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDTO;
import com.micro.services.supplier.svc.model.request.ProductAvailabilityRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAvailabilityRuleService {

    @Autowired
    private ProductAvailabilityRuleDao productAvailabilityRuleDao;

    public List<ProductAvailabilityRuleDTO> addAvailabilityRules(String productCode,
                                                                 List<ProductAvailabilityRule> productAvailabilityRules) {
        productAvailabilityRules.
                forEach(productAvailabilityRule -> productAvailabilityRuleDao.save(productCode, productAvailabilityRule));
        return productAvailabilityRuleDao.find(productCode);
    }

    private ProductAvailabilityRuleDTO constructProductAvailabilityDTO(String productCode,
                                                                       ProductAvailabilityRule productAvailabilityRule) {

        return new ProductAvailabilityRuleDTO(productCode,
                productAvailabilityRule.getStartDate(), productAvailabilityRule.getEndDate());
    }

}
