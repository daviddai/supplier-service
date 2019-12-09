package com.micro.services.supplier.svc.dao;

import com.micro.services.supplier.svc.dao.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Product product) {
        final String INSERT_PRODUCT = "insert into product values (?, ?, ?)";
        jdbcTemplate.update(INSERT_PRODUCT,
                product.getProductCode(), product.getProductName(), product.getProductDescription());
    }

    public Optional<Product> find(String productCode) {
        final String FIND_PRODUCT = "select * from product where product_code = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(FIND_PRODUCT, new ProductRowMapper(), productCode));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }


    private static class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Product(
                    rs.getString("product_code"),
                    rs.getString("product_name"),
                    rs.getString("product_description")
            );
        }

    }

}
