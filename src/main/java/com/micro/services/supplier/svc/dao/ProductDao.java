package com.micro.services.supplier.svc.dao;

import com.micro.services.supplier.svc.dao.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Product product) {
        final String INSERT_PRODUCT = "insert into product values (?, ?, ?)";
        jdbcTemplate.update(INSERT_PRODUCT,
                product.getProductCode(), product.getProductName(), product.getProductDescription());
    }

}
