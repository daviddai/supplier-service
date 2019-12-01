package com.micro.services.supplier.svc.service;

import com.micro.services.supplier.svc.dao.ProductDao;
import com.micro.services.supplier.svc.dao.model.Product;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;

    public Optional<ProductApiModel> create(CreateProductRequest request) {
        Product newProduct = constructProduct(request);
        productDao.save(newProduct);
        return Optional.of(
                new ProductApiModel(
                        newProduct.getProductCode(),
                        newProduct.getProductName(),
                        Optional.of(newProduct.getProductDescription()),
                        request.getProductAvailabilities())
        );
    }

    private Product constructProduct(CreateProductRequest request) {
        return new Product(
            generateProductCode(request.getProductName()),
            request.getProductName(),
            request.getProductDescription().orElse("")
        );
    }

    private String generateProductCode(String productName) {
        // todo: find a better way to generate product code
        return UUID.randomUUID().toString();
    }

}
