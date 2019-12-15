package com.micro.services.supplier.svc.service;

import com.micro.services.supplier.svc.dao.ProductAvailabilityRuleDao;
import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDTO;
import com.micro.services.supplier.svc.model.request.NewProductAvailabilityRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAvailabilityRuleService {

    @Autowired
    private ProductAvailabilityRuleDao productAvailabilityRuleDao;

    public List<ProductAvailabilityRuleDTO> getProductAvailabilityRules(String productCode) {
        return productAvailabilityRuleDao.find(productCode);
    }

    public void addAvailabilityRules(String productCode, List<NewProductAvailabilityRule> newProductAvailabilityRules) {
        newProductAvailabilityRules.
                forEach(productAvailabilityRule -> productAvailabilityRuleDao.save(productCode, productAvailabilityRule));
    }

    public void updateAvailabilityRules(List<ProductAvailabilityRuleDTO> productAvailabilityRuleDTOs) {
        productAvailabilityRuleDTOs.
                forEach(productAvailabilityRuleDTO -> productAvailabilityRuleDao.update(productAvailabilityRuleDTO));
    }

    public void removeAvailabilityRues(String productCode, List<Long> productAvailabilityRuleIds) {
        productAvailabilityRuleIds
                .forEach(productAvailabilityRuleId -> productAvailabilityRuleDao.delete(productCode, productAvailabilityRuleId));
    }
}
