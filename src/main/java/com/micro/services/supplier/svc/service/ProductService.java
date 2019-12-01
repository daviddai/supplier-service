package com.micro.services.supplier.svc.service;

import com.micro.services.supplier.svc.dao.ProductDao;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public Optional<ProductApiModel> create(CreateProductRequest request) {
        return Optional.of(
                new ProductApiModel(
                        generateProductCode(request.getProductName()),
                        request.getProductName(),
                        request.getProductDescription(),
                        request.getProductAvailabilities())
        );
    }

    private String generateProductCode(String productName) {
        // todo: find a better way to generate product code
        return UUID.randomUUID().toString();
    }

}
