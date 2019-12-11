package com.micro.services.supplier.svc.dao;

import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductAvailabilityRuleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(ProductAvailabilityRuleDTO productAvailabilityRuleDTO) {
        final String INSERT_PRODUCT_AVAILABILITY_RULE = "insert into product_availability_rule values (?, ?, ?)";
        jdbcTemplate.update(INSERT_PRODUCT_AVAILABILITY_RULE,
                productAvailabilityRuleDTO.getProductCode(),
                productAvailabilityRuleDTO.getStartDate(),
                productAvailabilityRuleDTO.getEndDate());
    }

    public List<ProductAvailabilityRuleDTO> find(String productCode) {
        return null;
    }

}
