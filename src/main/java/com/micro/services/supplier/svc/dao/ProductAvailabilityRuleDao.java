package com.micro.services.supplier.svc.dao;

import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDTO;
import com.micro.services.supplier.svc.model.request.ProductAvailabilityRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductAvailabilityRuleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(String productCode, ProductAvailabilityRule productAvailabilityRule) {
        final String INSERT_PRODUCT_AVAILABILITY_RULE = "insert into product_availability_rule values (?, ?, ?)";
        jdbcTemplate.update(INSERT_PRODUCT_AVAILABILITY_RULE,
                productCode,
                productAvailabilityRule.getStartDate(),
                productAvailabilityRule.getEndDate());
    }

    public List<ProductAvailabilityRuleDTO> find(String productCode) {
        final String FIND_PRODUCT_AVAILABILITY_RULES_BY_PRODUCT_CODE =
                "select * from product_availability_rule where product_code = ?";
        return jdbcTemplate.query(FIND_PRODUCT_AVAILABILITY_RULES_BY_PRODUCT_CODE,
                new ProductAvailabilityRuleRowMapper(),
                productCode);
    }

    private static class ProductAvailabilityRuleRowMapper implements RowMapper<ProductAvailabilityRuleDTO> {
        @Override
        public ProductAvailabilityRuleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return null;
        }
    }

}
