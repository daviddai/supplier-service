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

    private static final String TABLE_NAME = "product_availability_rule";

    public void save(String productCode, ProductAvailabilityRule productAvailabilityRule) {
        final String INSERT_PRODUCT_AVAILABILITY_RULE = "insert into " + TABLE_NAME + " values (?, ?, ?)";
        jdbcTemplate.update(INSERT_PRODUCT_AVAILABILITY_RULE,
                productCode,
                productAvailabilityRule.getStartDate(),
                productAvailabilityRule.getEndDate());
    }

    public List<ProductAvailabilityRuleDTO> find(String productCode) {
        final String FIND_PRODUCT_AVAILABILITY_RULES_BY_PRODUCT_CODE =
                "select * from " + TABLE_NAME + " where product_code = ?";
        return jdbcTemplate.query(FIND_PRODUCT_AVAILABILITY_RULES_BY_PRODUCT_CODE,
                new ProductAvailabilityRuleRowMapper(),
                productCode);
    }

    public void update(ProductAvailabilityRuleDTO productAvailabilityRuleDTO) {
        final String UPDATE_PRODUCT_AVAILABILITY_RULE =
                "update " + TABLE_NAME + " set start_date = ?, end_date = ? where product_code = ? and id = ?";
        jdbcTemplate.update(UPDATE_PRODUCT_AVAILABILITY_RULE,
                productAvailabilityRuleDTO.getStartDate(),
                productAvailabilityRuleDTO.getEndDate(),
                productAvailabilityRuleDTO.getProductCode(),
                productAvailabilityRuleDTO.getId());
    }

    public void delete(String productCode, long productAvailabilityRuleId) {
        final String DELETE_PRODUCT_AVAILABILITY_RULE = "delete from " + TABLE_NAME + " where product_code = ? and id = ?";
        jdbcTemplate.update(DELETE_PRODUCT_AVAILABILITY_RULE, productCode, productAvailabilityRuleId);
    }

    private static class ProductAvailabilityRuleRowMapper implements RowMapper<ProductAvailabilityRuleDTO> {
        @Override
        public ProductAvailabilityRuleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return null;
        }
    }

}
